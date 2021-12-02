package com.khaymoev.my_expenses.repository.category

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.local.database.ExpenseEntity
import javax.inject.Inject

class CategoriesListLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    /**
     * Получение полного списка категорий хранящихся в базе данных
     */
    val allCategoriesList: LiveData<List<CategoryEntity>> = database.expensesListDao().categoriesList()


    suspend fun getCategoriesListName(): List<CategoryEntity> {
        return database.expensesListDao().categoriesListName()
    }

    /**
     * Вставляет данные в базу данных
     *
     * @param category объект [CategoryEntity]
     */
    suspend fun insertCategoryIntoDatabase(category: CategoryEntity) {
        return database.expensesListDao().insertCategory(category)
    }
}