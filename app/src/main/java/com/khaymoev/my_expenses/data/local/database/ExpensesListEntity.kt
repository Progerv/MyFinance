package com.khaymoev.my_expenses.data.local.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Класс описывающий сущность хранящихся в [ExpensesDatabase] элементов
 *
 * @param id идентификатор расхода
 * @param name имя расхода
 *
 */

//    foreignKeys = [ForeignKey(entity = CategoriesListEntity::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("category"),
//        onDelete = CASCADE)]
//)
@Entity(tableName = "expenses_list")
data class ExpensesListEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val name: String,
    //val category: Long? = null,
    val amount: Float = 0F
)

//@Entity(tableName = "expenses_list", foreignKeys = [ForeignKey(entity = CategoriesListEntity::class,
//    parentColumns = arrayOf("id"),
//    childColumns = arrayOf("category"),
//    onDelete = ForeignKey.CASCADE
//)])
//data class ExpensesListEntity(
//    @PrimaryKey(autoGenerate = true) val id: Long?,
//    val name: String,
//    val category: Long? = null,
//    val amount: Float = 0F
//)
//
//@Entity(tableName = "categories_list")
//data class CategoriesListEntity(
//    @PrimaryKey(autoGenerate = true) val id: Long,
//    val name: String
//)