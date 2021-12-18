package com.khaymoev.my_expenses.repository.expensesList

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import javax.inject.Inject

class ExpensesListLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = database.expensesListDao().getCategoryWithExpenses()

    suspend fun insertExpenseIntoDatabase(expense: ExpenseEntity) {
        return database.expensesListDao().insertExpense(expense)
    }
}