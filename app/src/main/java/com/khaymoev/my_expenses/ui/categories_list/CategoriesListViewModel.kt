package com.khaymoev.my_expenses.ui.categories_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.CategoryEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import com.khaymoev.my_expenses.repository.currency.CurrencyListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesListViewModel @Inject constructor(private val repository: CategoriesListRepository,
                                                  private val repositoryCurrency: CurrencyListRepository
) :
    ViewModel() {

    /**
     * [CategoryEntity] хранит полный список категорий
     */
    val allCategoriesList: LiveData<List<CategoryEntity>> = repository.allCategoriesList

    fun addFirstCategories() {
        viewModelScope.launch(Dispatchers.IO)
        {
            addFirst()
        }
    }

    private suspend fun addFirst() {
        repository.insertCategory(
            CategoryEntity(
                id = 1,
                name = "Продукты"
            )
        )
        repository.insertCategory(
            CategoryEntity(
                id = 2,
                name = "Квартплата"
            )
        )
    }

    fun loadCurrencyFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryCurrency.currencyList()
        }
    }
}