package com.khaymoev.my_expenses.repository.category

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import javax.inject.Inject

class CategoriesListRepository @Inject constructor(
    private val categoriesListLocalDataSource: CategoriesListLocalDataSource
) {

    val allCategoriesList: LiveData<List<CategoryEntity>> = categoriesListLocalDataSource.allCategoriesList

    suspend fun getCategoriesListName(): List<CategoryEntity> {
        return categoriesListLocalDataSource.getCategoriesListName()
    }

    suspend fun insertCategory(category: CategoryEntity) {
        categoriesListLocalDataSource.insertCategoryIntoDatabase(category)
    }
}