package com.khaymoev.my_expenses.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_list")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)