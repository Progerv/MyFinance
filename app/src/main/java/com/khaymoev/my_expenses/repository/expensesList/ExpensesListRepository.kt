package com.khaymoev.my_expenses.repository.expensesList

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.ExpenseEntity
import javax.inject.Inject

class ExpensesListRepository @Inject constructor(
    private val expensesListLocalDataSource: ExpensesListLocalDataSource
) {
    /**
     * Список всех криптовалют
     */
    val allExpensesList: LiveData<List<ExpenseEntity>> = expensesListLocalDataSource.allExpensesList

    /**
     * Добавляем затрату в базу данных
     *
     * @param expense
     */
    suspend fun insertExpense(expense: ExpenseEntity) {
        expensesListLocalDataSource.insertExpenseIntoDatabase(expense)
    }
}