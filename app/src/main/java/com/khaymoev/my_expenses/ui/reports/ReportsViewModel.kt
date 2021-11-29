package com.khaymoev.my_expenses.ui.reports

import androidx.lifecycle.ViewModel
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(private val repository: ExpensesListRepository): ViewModel() {

    

}