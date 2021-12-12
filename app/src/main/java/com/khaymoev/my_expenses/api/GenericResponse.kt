package com.khaymoev.my_expenses.api

/**
 * Класс описывающий общий ответ с сервера
 *
 * @param code код ответа от сервера
 * @param message сообщение об ошибке
 */
data class GenericResponse(val code: Int, val message: String)
