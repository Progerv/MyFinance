package com.khaymoev.my_expenses.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Интерфейс [ExpensesListDao] содержащий функции-запросы к базе данных [ExpensesListEntity]
 */
@Dao
interface ExpensesListDao {

    /**
     * Функция для получения из базы данных всего её содержимого, т.е. полного списка затрат
     */
    @Query("SELECT * FROM expenses_list")
    fun expensesList(): LiveData<List<ExpensesListEntity>>

//    /**
//     * Функция для получения из базы данных всего её содержимого, т.е. полного списка категорий затрат
//     */
//    @Query("SELECT * FROM categories_list")
//    fun categoriesList(): LiveData<List<CategoriesListEntity>>

    /**
     * Функция для получения из базы данных конкретной затраты по id
     */
    @Query("SELECT * FROM expenses_list WHERE id = :id")
    fun expenseFromId(id: Long): ExpensesListEntity?

//    /**
//     * Функция для получения из базы данных конкретной категории затраты по id
//     */
//    @Query("SELECT * FROM categories_list WHERE id = :id")
//    suspend fun categoryFromId(id: Long): CategoriesListEntity?

    /**
     * Записывает данные в базу. Если какой-то объект уже имеется, он перезаписывается.
     *
     * @param list список объектов [ExpensesListEntity] которые необходимо записать в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(list: List<ExpensesListEntity>)

    /**
     * Записывает данные в базу. Если какой-то объект уже имеется, он перезаписывается.
     *
     * @param item объект [ExpensesListEntity] который необходимо записать в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(item: ExpensesListEntity)

    /**
     * Функция для удаления расхода по [id] из таблицы expenses_list из базы данных
     */
    @Query("DELETE FROM expenses_list WHERE id = :id")
    suspend fun deleteExpense(id: Long)

    /**
     * Функция для удаления всех элементов из базы данных
     */
    @Query("DELETE FROM expenses_list")
    suspend fun deleteAll()

//    /**
//     * Функция для удаления категории по [id] из таблицы categories_list из базы данных
//     */
//    @Query("DELETE FROM categories_list WHERE id = :id")
//    suspend fun deleteCategory(id: Long)
//
//    /**
//     * Функция для удаления всех элементов таблицы categories_list из базы данных
//     */
//    @Query("DELETE FROM categories_list")
//    suspend fun deleteAllCategories()
}