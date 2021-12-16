package com.khaymoev.my_expenses.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.khaymoev.my_expenses.data.local.database.DateConverter
import java.util.*

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
    val amountInRUB: Float = 0F,
    @TypeConverters(DateConverter::class)
    val dateExpense: Date
)