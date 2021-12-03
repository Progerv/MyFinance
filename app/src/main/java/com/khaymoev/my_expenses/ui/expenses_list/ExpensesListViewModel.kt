package com.khaymoev.my_expenses.ui.expenses_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.khaymoev.my_expenses.data.local.database.ExpenseEntity
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesListViewModel @Inject constructor(private val repository: ExpensesListRepository): ViewModel() {

    /**
     * [ExpenseEntity] хранит полный список затрат
     */

    val expensesListEntity: LiveData<List<ExpenseEntity>> = repository.allExpensesList

    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = repository.allCategoriesWithExpenses

    fun addD() {
        viewModelScope.launch(Dispatchers.IO)
        {
            addFirst()
        }
    }

    private suspend fun addFirst() {
        repository.insertExpense(
            ExpenseEntity(
                idExpense = 1,
                name = "Купил хлебушек",
                idCategory = 1,
                amount = 100F
            )
        )
    }

    /**
     * Добавление новой затраты
     * @param name наименование затраты
     * @param idCategory идентификатор категории
     * @param amount сумма затрат
     */
    fun addNewExpense(id: Long, name: String, idCategory: Long, amount: Float) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insertExpense(
                ExpenseEntity(
                    idExpense = id,
                    name = name,
                    idCategory = idCategory,
                    amount = amount
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
                        amount = expenseEntity.amount
                    )
                )
            }
        }

        return list
    }
}