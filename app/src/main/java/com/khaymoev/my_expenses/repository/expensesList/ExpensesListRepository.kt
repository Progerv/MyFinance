package com.khaymoev.my_expenses.repository.expensesList

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import javax.inject.Inject

class ExpensesListRepository @Inject constructor(
    private val expensesListLocalDataSource: ExpensesListLocalDataSource
) {
    /**
     * Список затрат без категорий
     */
    val allExpensesList: LiveData<List<ExpenseEntity>> = expensesListLocalDataSource.allExpensesList

    /**
     * Список затрат с категориями
     */
    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = expensesListLocalDataSource.allCategoriesWithExpenses

    suspend fun getStatistic(): List<CategoryWithExpenses> {
        return expensesListLocalDataSource.getStatistic()
    }

    /**
     * Добавляем затрату в базу данных
     *
     * @param expense
     */
    suspend fun insertExpense(expense: ExpenseEntity) {
        expensesListLocalDataSource.insertExpenseIntoDatabase(expense)
    }
}