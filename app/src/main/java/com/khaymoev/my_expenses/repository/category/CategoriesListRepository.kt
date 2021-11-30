package com.khaymoev.my_expenses.repository.category

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.CategoryEntity
import javax.inject.Inject

class CategoriesListRepository @Inject constructor(
    private val categoriesListLocalDataSource: CategoriesListLocalDataSource
) {
    /**
     * Список категорий
     */
    val allCategoriesList: LiveData<List<CategoryEntity>> = categoriesListLocalDataSource.allCategoriesList

    /**
     * Добавляем категорию в базу данных
     *
     * @param category
     */
    suspend fun insertCategory(category: CategoryEntity) {
        categoriesListLocalDataSource.insertCategoryIntoDatabase(category)
    }
}