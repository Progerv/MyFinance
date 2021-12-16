package com.khaymoev.my_expenses.data.local.database

import androidx.room.Embedded
import androidx.room.Relation
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity

data class CategoryWithExpenses (
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idCategory"
    )
    val expenses: List<ExpenseEntity>
)