package com.khaymoev.my_expenses.ui.categories_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesListViewModel @Inject constructor(
    private val repository: CategoriesListRepository
) :
    ViewModel() {

    val allCategoriesList: LiveData<List<CategoryEntity>> = repository.allCategoriesList
}