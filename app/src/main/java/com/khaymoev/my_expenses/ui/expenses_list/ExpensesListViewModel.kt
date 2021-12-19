package com.khaymoev.my_expenses.ui.expenses_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import com.khaymoev.my_expenses.ui.expenses_list.adapter.CategoriesWithExpensesForAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExpensesListViewModel @Inject constructor(private val repository: ExpensesListRepository): ViewModel() {

    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = repository.allCategoriesWithExpenses

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
                        currency = expenseEntity.currency,
                        date = expenseEntity.dateExpense
                    )
                )
            }
        }

        return list
    }
}