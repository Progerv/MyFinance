package com.khaymoev.my_expenses.api.model

data class CurrencyRemoteData(
    val base_code: String,
    val result: String,
    val time_next_update_unix: Int,
    val conversion_rates: ConversionRates?
)