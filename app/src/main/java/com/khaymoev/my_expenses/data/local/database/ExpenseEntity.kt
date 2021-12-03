package com.khaymoev.my_expenses.data.local.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * Класс описывающий сущность хранящихся в [ExpensesDatabase] элементов
 *
 * @param idExpense идентификатор расхода
 * @param name имя расхода
 * @param amount сумма расхода
 *
 */

@Entity(tableName = "expenses_list")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val idExpense: Long = 0,
    val name: String,
    val idCategory: Long,
    val amount: Float = 0F,
    val currency: String = "",
    val amountInUSD: Float = 0F
)