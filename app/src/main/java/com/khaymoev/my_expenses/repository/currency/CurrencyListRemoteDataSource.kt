package com.khaymoev.my_expenses.repository.currency

import com.khaymoev.my_expenses.api.ApiInterface
import com.khaymoev.my_expenses.api.BaseRemoteDataSource
import com.khaymoev.my_expenses.api.Result
import com.khaymoev.my_expenses.api.model.CurrencyRemoteData
import javax.inject.Inject

class CurrencyListRemoteDataSource @Inject constructor(private val service: ApiInterface): BaseRemoteDataSource() {

    suspend fun currencyList(targetCur: String): Result<CurrencyRemoteData> =
        getResult {
            service.currencyList(targetCur)
        }
}