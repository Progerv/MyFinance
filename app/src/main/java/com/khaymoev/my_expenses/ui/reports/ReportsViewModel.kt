package com.khaymoev.my_expenses.ui.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.khaymoev.my_expenses.data.local.database.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.repository.expensesList.ExpensesListRepository
import com.khaymoev.my_expenses.ui.expenses_list.CategoriesWithExpensesForAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(private val repository: ExpensesListRepository): ViewModel() {

    /**
     * [CategoryEntity] хранит полный список категорий
     */
    val allCategoriesWithExpenses: LiveData<List<CategoryWithExpenses>> = repository.allCategoriesWithExpenses

    fun withDatabaseTableToListView(listDatabase: List<CategoryWithExpenses>): ArrayList<Statistic> {

        val list: ArrayList<Statistic> = mutableListOf<Statistic>() as ArrayList<Statistic>

        listDatabase.forEach { categoryWithExpenses: CategoryWithExpenses ->
            categoryWithExpenses.expenses.forEach { expenseEntity ->
                list.add(
                    Statistic(
                        category = categoryWithExpenses.category.name,
                        expenseAmount = expenseEntity.amount.toDouble()
                    )
                )
            }
        }

        return list
    }
}