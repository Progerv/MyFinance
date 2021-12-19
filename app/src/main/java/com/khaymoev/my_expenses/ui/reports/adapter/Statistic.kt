package com.khaymoev.my_expenses.ui.reports.adapter

data class Statistic(val category: String,
                     var expenseAmount: Double,
                     var operationCount: Int,
                     var color: String)