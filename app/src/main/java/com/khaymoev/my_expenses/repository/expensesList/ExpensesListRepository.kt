package com.khaymoev.my_expenses.repository.expensesList

import com.khaymoev.my_expenses.common.Result
import com.khaymoev.my_expenses.models.Expense
import com.khaymoev.my_expenses.utils.Constants
import javax.inject.Inject

class ExpensesListRepository @Inject constructor(
    private val expensesListLocalDataSource: ExpensesListLocalDataSource
) {
    /**
     * Список всех криптовалют
     */
    val allCoinsList = expensesListLocalDataSource.allExpensesList

    /**
     * Добавляем затрату в базу данных
     *
     * @param expense
     */
    suspend fun insertExpense(expense: Expense) {
        expensesListLocalDataSource.insertExpenseIntoDatabase(expense)
    }
}