package com.khaymoev.my_expenses.ui.expenses_list.expense_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.entities.CurrencyEntity
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import com.khaymoev.my_expenses.repository.category.CategoriesListRepository
import com.khaymoev.my_expenses.repository.currency.CurrencyListRepository
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ExpenseEditViewModel @Inject constructor(
    private val repository: CategoriesListRepository,
    private val repositoryExpenses: ExpensesListRepository,
    private val repositoryCurrency: CurrencyListRepository
) :
    ViewModel() {

    private var currencyList: List<CurrencyEntity>? = null

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

    fun updateCurrencies() {
        viewModelScope.launch(Dispatchers.IO)
        {
            currencyList = repositoryCurrency.currencyList()
        }
    }

    fun getUpdatedText() {
        val updatedText = model
        uiTextLiveData.postValue(updatedText)
    }

    fun addExpense(
        name: String,
        nameCategory: String = "",
        amount: Float,
        currency: String
    ) {

        var amountRUB = 0F
        val currencyEntity = currencyList?.find { it.currencyName == currency }
        amountRUB = if (currencyEntity != null) {
                if (currencyEntity.currencyValue != 0.0) {
                    (amount / currencyEntity.currencyValue).toFloat().also { amountRUB = it }
                } else {
                    amount
                }
        } else {
            amount
        }
        viewModelScope.launch(Dispatchers.IO)
        {
            repositoryExpenses.insertExpense(
                ExpenseEntity(
                    name = name,
                    idCategory = categoryList!!.first { it.name == nameCategory }.id,
                    amount = amount,
                    currency = currency,
                    dateExpense = Date(),
                    amountInRUB = amountRUB
                )
            )
        }
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//fun updateExpense(id: Long, name: String, idCategory: Long = 1, nameCategory: String = "", amount: Float) {
//    viewModelScope.launch(Dispatchers.IO)
//    {
//        repositoryExpenses.insertExpense(
//            ExpenseEntity(
//                idExpense = id,
//                name = name,
//                idCategory = idCategory,
//                amount = amount,
//                dateExpense = Date()
//            )
//        )
//    }
//}