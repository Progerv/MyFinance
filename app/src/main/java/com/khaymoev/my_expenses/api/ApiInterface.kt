package com.khaymoev.my_expenses.api

import com.khaymoev.my_expenses.api.model.CurrencyRemoteData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Интерфейс с функциями запроса данных из сети
 */
interface ApiInterface {
    /**
     * Функция для запроса списка криптовалют
     *
     * @param targetCurrency валюта в которой будет указана цена криптовалюты
     * "https://v6.exchangerate-api.com/"
     * v6.exchangerate-api.com/v6/2cd7f2cc99cc84e2d307c858/latest/RUB
     *
     * https://v6.exchangerate-api.com/v6/2cd7f2cc99cc84e2d307c858/latest/RUB
     */
    @GET("v6/2cd7f2cc99cc84e2d307c858/latest/{targetCurrency}")
    suspend fun currencyList(@Path("targetCurrency") targetCurrency: String): Response<CurrencyRemoteData>
}