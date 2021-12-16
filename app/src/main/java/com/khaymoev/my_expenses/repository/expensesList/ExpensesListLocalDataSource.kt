package com.khaymoev.my_expenses.repository.expensesList

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import javax.inject.Inject

class ExpensesListLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    /**
     * Получение полного списка затрат хранящихся в базе данных
     */
    val allExpensesList: LiveData<List<ExpenseEntity>> = database.expensesListDao().expensesList()

    /**
     * Получение полного списка затрат хранящихся в базе данных
     */
    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = database.expensesListDao().getCategoryWithExpenses()

    suspend fun getStatistic(): List<CategoryWithExpenses> {
        return database.expensesListDao().getStatistic()
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