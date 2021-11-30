package com.khaymoev.my_expenses.repository.expense

import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import javax.inject.Inject

/**
 * Класс [ExpenseLocalDataSource] выступает источником локальны данных
 * для получения информации о конкретной криптовалюте
 *
 * @param database экземпляр класса [ExpensesDatabase]
 */
class ExpenseLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    /**
     * Выполняет поиск затраты по её id внутри базы данных
     *
     * @param id
     */
    fun expenseFromId(id: Long) = database.expensesListDao().expenseFromId(id)
}