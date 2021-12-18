package com.khaymoev.my_expenses.ui.expenses_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import com.khaymoev.my_expenses.ui.expenses_list.adapter.CategoriesWithExpensesForAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExpensesListViewModel @Inject constructor(private val repository: ExpensesListRepository): ViewModel() {

    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = repository.allCategoriesWithExpenses

    fun addTestData() {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insertExpense(
                ExpenseEntity(
                    idExpense = 1,
                    name = "Купил хлебушек",
                    idCategory = 1,
                    amount = 100F,
                    dateExpense = Date(),
                    currency = "RUB",
                    amountInRUB = 100F
                )
            )
        }
    }

    fun withDatabaseTableToListView(listDatabase: List<CategoryWithExpenses>): List<CategoriesWithExpensesForAdapter> {

        val list: MutableList<CategoriesWithExpensesForAdapter> = mutableListOf()

        listDatabase.forEach {
                categoryWithExpenses: CategoryWithExpenses ->
            categoryWithExpenses.expenses.forEach {
                    expenseEntity ->
                list.add(
                    CategoriesWithExpensesForAdapter(
                        idExpense = expenseEntity.idExpense,
                        name = expenseEntity.name,
                        nameCategory = categoryWithExpenses.category.name,
                        amount = expenseEntity.amount,
                        currency = expenseEntity.currency
                    )
                )
            }
        }

        return list
    }
}