package com.khaymoev.my_expenses.ui.expenses_list

data class CategoriesWithExpensesForAdapter(
    val idExpense: Long,
    val name: String,
    val nameCategory: String,
    val amount: Float = 0F
)