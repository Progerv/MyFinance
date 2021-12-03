package com.khaymoev.my_expenses.utils

import java.text.DecimalFormat

//проверяет строку на null, и при необходимости возвращает пустую строку
fun String?.emptyIfNull(): String {
    return this ?: ""
}

//добавляет в строку знак доллара
fun Double?.dollarString(): String {
    return this?.let {
        val numberFormat = DecimalFormat("#,##0.00")
        "$ ${numberFormat.format(this)}"
    } ?: ""
}

//добавляет в строку знак доллара
fun Float?.refactorString(): String {
    return this?.let {
        val numberFormat = DecimalFormat("#,##0.00")
        "$ ${numberFormat.format(this)}" //TODO значек валюты в зависимости от валюты
    } ?: ""
}