package com.khaymoev.my_expenses.repository.currency

import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.local.database.entities.CurrencyEntity
import javax.inject.Inject

class CurrencyLocalDataSource @Inject constructor(private val database: ExpensesDatabase) {

    suspend fun currencyList(): List<CurrencyEntity> {
        return database.expensesListDao().currencyList()
    }
    /**
     * Вставляет данные в базу данных
     *
     * @param currency объект [CurrencyEntity]
     */
    suspend fun insertCurrencyIntoDatabase(currency: CurrencyEntity) {
        return database.expensesListDao().insertCurrency(currency)
    }
}