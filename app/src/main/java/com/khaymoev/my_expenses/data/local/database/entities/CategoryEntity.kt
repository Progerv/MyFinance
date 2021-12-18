package com.khaymoev.my_expenses.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_list")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    //format color - HEX, example #008000 - green color
    val colorCategory: String?
)