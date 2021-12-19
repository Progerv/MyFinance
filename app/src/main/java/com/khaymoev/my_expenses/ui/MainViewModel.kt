package com.khaymoev.my_expenses.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import com.khaymoev.my_expenses.repository.currency.CurrencyListRepository
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * ViewModel для класса [MainActivity] использовать не будем,
 * создаем для целостности приложения с точки зрения подхода
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryCurrency: CurrencyListRepository,
    private val repositoryCategory: CategoriesListRepository,
    private val repositoryExpenses: ExpensesListRepository) :
    ViewModel() {

    fun loadCurrencyFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryCurrency.currencyListUpdate()
        }
    }

    //TODO Перенести создание категорий в файл ExpensesDatabase, чтобы создавались только 1 раз, пока не разобрался, как сделать
    fun addFirstCategories() {
        viewModelScope.launch(Dispatchers.IO)
        {
            repositoryCategory.insertCategory(
                CategoryEntity(
                    id = 1,
                    name = "Продукты",
                    colorCategory = "#99ff99"
                )
            )
            repositoryCategory.insertCategory(
                CategoryEntity(
                    id = 2,
                    name = "Квартплата",
                    colorCategory = "#ffff66"
                )
            )
            repositoryCategory.insertCategory(
                CategoryEntity(
                    id = 3,
                    name = "Одежда",
                    colorCategory = "#1ed2e3"
                )
            )
        }
    }

    //Тестовые данные - только для примера отображения, чтобы меньше вводить вручную
    fun addTestData() {
        viewModelScope.launch(Dispatchers.IO)
        {
            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    idExpense = 1,
                    name = "Хлебушек",
                    idCategory = 1,
                    amount = 68F,
                    dateExpense = Date(1639843200 * 1000L), //18/12/21
                    currency = "RUB",
                    amountInRUB = 68F
                )
            )

            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    idExpense = 2,
                    name = "Электричество",
                    idCategory = 2,
                    amount = 1000F,
                    dateExpense = Date(1639930644 * 1000L), //19/12/21
                    currency = "RUB",
                    amountInRUB = 1000F
                )
            )

            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    idExpense = 3,
                    name = "Куртка",
                    idCategory = 3,
                    amount = 110F,
                    dateExpense = Date(1639930644 * 1000L), //19/12/21
                    currency = "USB",
                    amountInRUB = 8110F
                )
            )

            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    idExpense = 4,
                    name = "Продукты на неделю в Ленте",
                    idCategory = 1,
                    amount = 4000F,
                    dateExpense = Date(1639930644 * 1000L), //19/12/21
                    currency = "RUB",
                    amountInRUB = 4000F
                )
            )

            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    idExpense = 5,
                    name = "Кетчуп",
                    idCategory = 1,
                    amount = 70F,
                    dateExpense = Date(),
                    currency = "RUB",
                    amountInRUB = 70F
                )
            )

            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    idExpense = 6,
                    name = "Вода",
                    idCategory = 2,
                    amount = 900F,
                    dateExpense = Date(1639930644 * 1000L), //19/12/21
                    currency = "RUB",
                    amountInRUB = 900F
                )
            )
        }
    }
}