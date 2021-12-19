package com.khaymoev.my_expenses.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.entities.CurrencyEntity
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity

/**
 * Интерфейс [ExpensesListDao] содержащий функции-запросы к базе данных [ExpenseEntity]
 */
@Dao
interface ExpensesListDao {

    /**
     * Функция для получения из базы данных полного списка затрат
     */
    @Query("SELECT * FROM expenses_list")
    fun expensesList(): LiveData<List<ExpenseEntity>>

    /**
     * Функция для получения из базы данных список валют
     */
    @Query("SELECT * FROM conversion_rates")
    suspend fun currencyList(): List<CurrencyEntity>

    /**
     * Записывает объект [CurrencyEntity] в базу данных.
     *
     * @param item объект [CurrencyEntity] который необходимо записать в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(item: CurrencyEntity)

    /**
     * Функция для получения из базы данных списка затрат с категориями
     */
    @Transaction
    @Query("SELECT * FROM categories_list")
    fun getCategoryWithExpenses(): LiveData<List<CategoryWithExpenses>>

    //TODO - Сделать метод, который берет статистику по затратам за период
    /*
    @Transaction
    @Query("SELECT * FROM categories_list")
    suspend fun getStatistic(): List<CategoryWithExpenses>
    */

    /**
     * Функция для получения из базы данных списка категорий
     */
    @Query("SELECT * FROM categories_list")
    fun categoriesList(): LiveData<List<CategoryEntity>>

    /**
     * Функция для получения из базы данных списка категорий
     */
    @Query("SELECT id, name FROM categories_list")
    suspend fun categoriesListName(): List<CategoryEntity>

    /**
     * Функция для получения из базы данных конкретной затраты по id
     */
    @Query("SELECT * FROM expenses_list WHERE idExpense = :id")
    fun expenseFromId(id: Long): ExpenseEntity?

    /**
     * Функция для получения из базы данных конкретной категории по id
     */
    @Query("SELECT * FROM categories_list WHERE id = :id")
    fun categoryFromId(id: Long): CategoryEntity?

    /**
     * Записывает данные в базу объект [ExpenseEntity] затрату
     *
     * @param item объект [ExpenseEntity] который необходимо записать в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(item: ExpenseEntity)

    /**
     * Записывает объект [CategoryEntity] в базу данных.
     *
     * @param item объект [CategoryEntity] который необходимо записать в базу данных
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(item: CategoryEntity)

    /**
     * Функция для удаления затраты по [id] из таблицы expenses_list из базы данных
     */
    @Query("DELETE FROM expenses_list WHERE idExpense = :id")
    suspend fun deleteExpense(id: Long)

    /**
     * Функция для удаления всех элементов из таблицы expenses_list из базы данных
     */
    @Query("DELETE FROM expenses_list")
    suspend fun deleteAll()

    /**
     * Функция для удаления категории по [id] из таблицы categories_list из базы данных
     */
    @Query("DELETE FROM categories_list WHERE id = :id")
    suspend fun deleteCategory(id: Long)

    /**
     * Функция для удаления всех элементов из таблицы categories_list
     */
    @Query("DELETE FROM categories_list")
    suspend fun deleteCategoriesAll()
}