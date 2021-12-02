package com.khaymoev.my_expenses.ui.expenses_list.expense_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.CategoryEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExpenseEditViewModel @Inject constructor(private val repository: CategoriesListRepository) :
    ViewModel() {

    /**
     * [CategoryEntity] хранит полный список категорий
     */
    val allCategoriesList: LiveData<List<CategoryEntity>> = repository.allCategoriesList

    fun getListCategory(listCategory: List<CategoryEntity>): List<String> {

        return listCategory.map {
            it.name
        }
    }
}