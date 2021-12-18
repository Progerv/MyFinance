package com.khaymoev.my_expenses.repository.expensesList

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import javax.inject.Inject

class ExpensesListRepository @Inject constructor(
    private val expensesListLocalDataSource: ExpensesListLocalDataSource
) {

    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = expensesListLocalDataSource.allCategoriesWithExpenses

    suspend fun insertExpense(expense: ExpenseEntity) {
        expensesListLocalDataSource.insertExpenseIntoDatabase(expense)
    }
}