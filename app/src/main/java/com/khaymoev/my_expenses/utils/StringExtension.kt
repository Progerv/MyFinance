package com.khaymoev.my_expenses.utils

import java.text.DecimalFormat

fun Number?.refactorString(): String {
    return this?.let {
        val numberFormat = DecimalFormat("#,##0.00")
        numberFormat.format(this)
    } ?: ""
}

fun Int?.convertColorToHEX(): String {
    return this?.let { "#" + Integer.toHexString(this) } ?: ""
}