package com.khaymoev.my_expenses.repository.expensesList

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.local.database.ExpensesListEntity
import com.khaymoev.my_expenses.models.Expense
import javax.inject.Inject

class ExpensesListLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    /**
     * Получение полного списка затрат хранящихся в базе данных
     */
    val allExpensesList: LiveData<List<ExpensesListEntity>> = database.expensesListDao().expensesList()

    /**
     * Вставляет данные в базу данных
     *
     * @param expensesToInsert список объкетов [ExpensesListEntity]
     */
    suspend fun insertExpensesIntoDatabase(expensesToInsert: List<ExpensesListEntity>) {
        //проверяем список на пустоту
        if (expensesToInsert.isNotEmpty()) {
            //если список не пустой вставляем список в базу данных
            database.expensesListDao().insertExpenses(expensesToInsert)
        }
    }

    /**
     * Вставляет данные в базу данных
     *
     * @param expense объект [ExpensesListEntity]
     */
    suspend fun insertExpenseIntoDatabase(expense: Expense) {
        val expenseToInsert = ExpensesListEntity(
            expense.id,
            expense.name,
            expense.amount
        )
        return database.expensesListDao().insertExpense(expenseToInsert)
    }
}