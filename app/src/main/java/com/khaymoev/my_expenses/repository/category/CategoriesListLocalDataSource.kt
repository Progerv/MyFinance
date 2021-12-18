package com.khaymoev.my_expenses.repository.category

import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import javax.inject.Inject

class CategoriesListLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    val allCategoriesList: LiveData<List<CategoryEntity>> = database.expensesListDao().categoriesList()

    suspend fun getCategoriesListName(): List<CategoryEntity> {
        return database.expensesListDao().categoriesListName()
    }

    suspend fun insertCategoryIntoDatabase(category: CategoryEntity) {
        return database.expensesListDao().insertCategory(category)
    }
}