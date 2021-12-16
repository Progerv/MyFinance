package com.khaymoev.my_expenses.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_rates")
data class CurrencyEntity(
    @PrimaryKey val currencyName: String,
    val currencyValue: Double
)