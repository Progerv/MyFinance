package com.khaymoev.my_expenses.ui.categories_list.category_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryEditViewModel @Inject constructor(private val repository: CategoriesListRepository) :
    ViewModel() {

    fun addNewCategory(name: String, color: String = "#000000") {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insertCategory(
                CategoryEntity(
                    name = name,
                    colorCategory = color
                )
            )
        }
    }
}