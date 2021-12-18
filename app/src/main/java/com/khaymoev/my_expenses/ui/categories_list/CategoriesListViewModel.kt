package com.khaymoev.my_expenses.ui.categories_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesListViewModel @Inject constructor(
    private val repository: CategoriesListRepository
) :
    ViewModel() {

    val allCategoriesList: LiveData<List<CategoryEntity>> = repository.allCategoriesList

    fun addFirstCategories() {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insertCategory(
                CategoryEntity(
                    id = 1,
                    name = "Продукты",
                    colorCategory = "#99ff99"
                )
            )
            repository.insertCategory(
                CategoryEntity(
                    id = 2,
                    name = "Квартплата",
                    colorCategory = "#ffff66"
                )
            )
        }
    }
}