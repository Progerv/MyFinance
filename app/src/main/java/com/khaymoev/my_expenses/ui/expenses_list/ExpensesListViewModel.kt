package com.khaymoev.my_expenses.ui.expenses_list

import androidx.lifecycle.ViewModel
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExpensesListViewModel @Inject constructor(private val repository: ExpensesListRepository): ViewModel() {



}