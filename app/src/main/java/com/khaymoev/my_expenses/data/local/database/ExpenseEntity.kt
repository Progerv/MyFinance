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
//    ,
//    foreignKeys = [
//        ForeignKey(
//            entity = CategoryEntity::class,
//            parentColumns = ["id"],
//            childColumns = ["idCategory"],
//            onDelete = CASCADE,
//            onUpdate = CASCADE,
//            deferred = true
//        )
//    ])
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val idExpense: Long,
    val name: String,
    val idCategory: Int,
    val amount: Float = 0F
)