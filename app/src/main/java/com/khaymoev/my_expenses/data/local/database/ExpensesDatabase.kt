package com.khaymoev.my_expenses.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.entities.CurrencyEntity
import com.khaymoev.my_expenses.data.local.database.entities.ExpenseEntity
import com.khaymoev.my_expenses.utils.Constants.DATABASE_NAME
import com.khaymoev.my_expenses.utils.Constants.DATABASE_VERSION

/**
 * Класс [ExpensesDatabase] управляет базой данных Room внутри приложения
 */
@Database(entities = [ExpenseEntity::class, CategoryEntity::class, CurrencyEntity::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ExpensesDatabase: RoomDatabase() {
    abstract fun expensesListDao(): ExpensesListDao

    companion object {
        /**
         * Функция для сборки базы данных
         */
        fun buildDatabase(context: Context): ExpensesDatabase {
            return Room
                .databaseBuilder(context, ExpensesDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}