package com.khaymoev.my_expenses.repository.currency

import com.khaymoev.my_expenses.data.local.database.CategoryEntity
import com.khaymoev.my_expenses.data.local.database.CurrencyEntity
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import javax.inject.Inject

class CurrencyLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    /**
     * Вставляет данные в базу данных
     *
     * @param currency объект [CurrencyEntity]
     */
    suspend fun insertCurrencyIntoDatabase(currency: CurrencyEntity) {
        return database.expensesListDao().insertCurrency(currency)
    }

}