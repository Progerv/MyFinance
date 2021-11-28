package com.khaymoev.my_expenses.models

/**
 * Класс описывающий модель для данных о криптовалютах которые будут получены из сети
 *
 * @param id идентификатор криптовалюты
 * @param name сокращенное имя криптовалюты
 */
data class Category(
    val id: Int,
    val name: String
)
