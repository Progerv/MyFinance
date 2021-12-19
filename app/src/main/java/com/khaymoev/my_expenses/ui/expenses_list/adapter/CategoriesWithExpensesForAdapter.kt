package com.khaymoev.my_expenses.ui.expenses_list.adapter

import java.util.*

data class CategoriesWithExpensesForAdapter(
    val idExpense: Long,
    val name: String,
    val nameCategory: String,
    val amount: Float = 0F,
    val currency: String,
    val date: Date
)