package com.khaymoev.my_expenses.ui.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import com.khaymoev.my_expenses.ui.reports.adapter.Statistic
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(val repository: ExpensesListRepository): ViewModel() {

    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = repository.allCategoriesWithExpenses

    fun withDatabaseTableToListView(listDatabase: List<CategoryWithExpenses> ,dateFrom: Date ,dateTo: Date): ArrayList<Statistic> {

        val list: ArrayList<Statistic> = mutableListOf<Statistic>() as ArrayList<Statistic>

        listDatabase.forEach { categoryWithExpenses: CategoryWithExpenses ->
            categoryWithExpenses.expenses.forEach { expenseEntity ->
                if (expenseEntity.dateExpense in dateFrom..dateTo) {
                    val item = list.find { it.category == categoryWithExpenses.category.name }
                    if (item == null) {
                        list.add(
                            Statistic(
                                category = categoryWithExpenses.category.name,
                                expenseAmount = expenseEntity.amountInRUB.toDouble(),
                                1,
                                color = categoryWithExpenses.category.colorCategory ?: "#ffffff"
                            )
                        )
                    } else {
                        item.expenseAmount += expenseEntity.amountInRUB
                        item.operationCount ++
                    }
                }
            }
        }

        return list
    }
}