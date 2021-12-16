package com.khaymoev.my_expenses.utils

import java.text.DecimalFormat

fun Number?.refactorString(): String {
    return this?.let {
        val numberFormat = DecimalFormat("#,##0.00")
        numberFormat.format(this)
    } ?: ""
}