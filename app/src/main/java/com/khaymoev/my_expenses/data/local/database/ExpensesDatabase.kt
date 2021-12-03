package com.khaymoev.my_expenses.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.khaymoev.my_expenses.utils.Constants.DATABASE_NAME
import com.khaymoev.my_expenses.utils.Constants.DATABASE_VERSION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Класс [ExpensesDatabase] управляет базой данных Room внутри приложения
 */
@Database(entities = [ExpenseEntity::class, CategoryEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class ExpensesDatabase: RoomDatabase() {
    abstract fun expensesListDao(): ExpensesListDao

    companion object {

//        @Volatile
//        private var INSTANCE: ExpensesDatabase? = null
        /**
         * Функция для сборки базы данных
         */
        fun buildDatabase(context: Context, scope: CoroutineScope): ExpensesDatabase {
            return Room
                .databaseBuilder(context, ExpensesDatabase::class.java, DATABASE_NAME)
                //.addCallback(ExpensesDatabaseCallback(scope))
                .build()
        }
    }

//    private class ExpensesDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//        /**
//         * Override the onCreate method to populate the database.
//         */
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            // If you want to keep the data through app restarts,
//            // comment out the following line.
//            INSTANCE?.let { database ->
//                scope.launch(Dispatchers.IO) {
//                    populateDatabase(database.wordDao())
//                }
//            }
//        }
//    }
//
//    /**
//     * Populate the database in a new coroutine.
//     * If you want to start with more words, just add them.
//     */
//    suspend fun populateDatabase(wordDao: CategoryEntity) {
        // Start the app with a clean database every time.
        // Not needed if you only populate on creation.
//        wordDao.deleteAll()
//
//        var word = Word("Hello")
//        wordDao.insert(word)
//        word = Word("World!")
//        wordDao.insert(word)
//    }

}