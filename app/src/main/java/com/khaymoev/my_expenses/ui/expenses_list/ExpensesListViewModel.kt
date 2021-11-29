package com.khaymoev.my_expenses.ui.expenses_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.data.local.database.ExpensesListEntity
import com.khaymoev.my_expenses.models.Expense
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesListViewModel @Inject constructor(private val repository: ExpensesListRepository): ViewModel() {

    /**
     * [ExpensesListEntity] хранит полный список затрат
     */
    val expensesListEntity: LiveData<List<ExpensesListEntity>> = repository.allExpensesList


//    fun addD() {
//        viewModelScope.launch(Dispatchers.IO)
//        {
//            addFirst()
//        }
//    }
//
//    private suspend fun addFirst() {
//        repository.insertExpense(
//            Expense(
//                null,
//                "Первая затрата",
//                1000F
//            )
//        )
//    }

}