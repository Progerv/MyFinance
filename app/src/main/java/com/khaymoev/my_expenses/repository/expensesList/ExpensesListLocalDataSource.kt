package com.khaymoev.my_expenses.repository.expensesList

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.local.database.ExpenseEntity
import javax.inject.Inject

class ExpensesListLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    /**
     * Получение полного списка затрат хранящихся в базе данных
     */
    val allExpensesList: LiveData<List<ExpenseEntity>> = database.expensesListDao().expensesList()

    /**
     * Вставляет данные в базу данных
     *
     * @param expensesToInsert список объкетов [ExpenseEntity]
     */
    suspend fun insertExpensesIntoDatabase(expensesToInsert: List<ExpenseEntity>) {
        //проверяем список на пустоту
        if (expensesToInsert.isNotEmpty()) {
            //если список не пустой вставляем список в базу данных
            database.expensesListDao().insertExpenses(expensesToInsert)
        }
    }

    /**
     * Вставляет данные в базу данных
     *
     * @param expense объект [ExpenseEntity]
     */
    suspend fun insertExpenseIntoDatabase(expense: ExpenseEntity) {
        return database.expensesListDao().insertExpense(expense)
    }
}