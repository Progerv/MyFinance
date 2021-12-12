package com.khaymoev.my_expenses.ui.expenses_list.expense_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.ExpenseEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExpenseEditViewModel @Inject constructor(private val repository: CategoriesListRepository,private val repositoryExpenses: ExpensesListRepository) :
    ViewModel() {

    /**
     * [CategoryEntity] хранит полный список категорий
     */
    val allCategoriesList: LiveData<List<CategoryEntity>> = repository.allCategoriesList

    private var categoryList: List<CategoryEntity>? = null
    // Create the model which contains data for our UI
    private var model: List<String> = listOf()

    // Create MutableLiveData which MainFragment can subscribe to
    // When this data changes, it triggers the UI to do an update
    val uiTextLiveData = MutableLiveData<List<String>>()

    fun setListCategories() {
        viewModelScope.launch(Dispatchers.IO)
        {
            categoryList = repository.getCategoriesListName()
            model = categoryList!!.map { it.name }

        }
    }

    // Get the updated text from our model and post the value to MainFragment
    fun getUpdatedText() {
        val updatedText = model
        uiTextLiveData.postValue(updatedText)
    }

    fun updateExpense(id: Long, name: String, idCategory: Long = 1, nameCategory: String = "", amount: Float) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    idExpense = id,
                    name = name,
                    idCategory = idCategory,
                    amount = amount,
                    dateExpense = Date()
                )
            )
        }
    }

    fun addExpense(name: String, idCategory: Long = 1, nameCategory: String = "", amount: Float, currency: String) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    name = name,
                    idCategory = categoryList!!.first { it.name == nameCategory }.id,
                    amount = amount,
                    currency = currency,
                    dateExpense = Date()
                )
            )

        }
    }
}