package com.khaymoev.my_expenses.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Интерфейс [ExpensesListDao] содержащий функции-запросы к базе данных [ExpenseEntity]
 */
@Dao
interface ExpensesListDao {

    /**
     * Функция для получения из базы данных всего её содержимого, т.е. полного списка затрат
     */
    @Query("SELECT * FROM expenses_list")
    fun expensesList(): LiveData<List<ExpenseEntity>>

    /**
     * Функция для получения из базы данных списка затрат с категориями
     */
    @Transaction
    @Query("SELECT * FROM categories_list")
    suspend fun getCategoryWithExpenses(): List<CategoryWithExpenses>

    /**
     * Функция для получения из базы данных конкретной затраты по id
     */
    @Query("SELECT * FROM expenses_list WHERE idExpense = :id")
    fun expenseFromId(id: Long): ExpenseEntity?

    /**
     * Записывает данные в базу. Если какой-то объект уже имеется, он перезаписывается.
     *
     * @param list список объектов [ExpenseEntity] которые необходимо записать в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(list: List<ExpenseEntity>)

    /**
     * Записывает данные в базу. Если какой-то объект уже имеется, он перезаписывается.
     *
     * @param item объект [ExpenseEntity] который необходимо записать в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(item: ExpenseEntity)

    /**
     * Функция для удаления расхода по [id] из таблицы expenses_list из базы данных
     */
    @Query("DELETE FROM expenses_list WHERE idExpense = :id")
    suspend fun deleteExpense(id: Long)

    /**
     * Функция для удаления всех элементов из базы данных
     */
    @Query("DELETE FROM expenses_list")
    suspend fun deleteAll()
}