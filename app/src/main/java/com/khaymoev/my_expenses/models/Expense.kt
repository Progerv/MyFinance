package com.khaymoev.my_expenses.models

/**
 * Класс описывающий модель для данных о криптовалютах которые будут получены из сети
 *
 * @param id идентификатор расхода
 * @param name наименование расхода
 */
data class Expense(
    val id: Long? = null,
    val name: String,
    val amount: Float = 0F
)
