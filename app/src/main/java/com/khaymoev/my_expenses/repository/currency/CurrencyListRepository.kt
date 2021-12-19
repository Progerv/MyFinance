package com.khaymoev.my_expenses.repository.currency

import com.khaymoev.my_expenses.data.preferences.PreferenceStorage
import javax.inject.Inject
import com.khaymoev.my_expenses.api.Result
import com.khaymoev.my_expenses.api.successed
import com.khaymoev.my_expenses.data.local.database.entities.CurrencyEntity
import com.khaymoev.my_expenses.utils.Constants
import java.util.*

class CurrencyListRepository @Inject constructor(
    private val currencyListRemoteDataSource: CurrencyListRemoteDataSource,
    private val sharedPreferencesStorage: PreferenceStorage,
    private val currencyLocalDataSource: CurrencyLocalDataSource
) {

    suspend fun currencyListUpdate(): String {

        when (val result =
            currencyListRemoteDataSource.currencyList(sharedPreferencesStorage.currencyDefault)) {
            is Result.Success -> {

                if (result.successed) {

                    result.data.let {

                            listCurr ->
                        val anyCurrency = listCurr.conversion_rates

                        if (anyCurrency != null) {
                            currencyLocalDataSource.insertCurrencyIntoDatabase(
                                CurrencyEntity(
                                    currencyName = "RUB",
                                    currencyValue = anyCurrency.RUB.toDouble(),
                                    date = getStartOfDay()
                                )
                            )

                            currencyLocalDataSource.insertCurrencyIntoDatabase(
                                CurrencyEntity(
                                    currencyName = "EUR",
                                    currencyValue = anyCurrency.EUR,
                                    date = getStartOfDay()
                                )
                            )

                            currencyLocalDataSource.insertCurrencyIntoDatabase(
                                CurrencyEntity(
                                    currencyName = "USD",
                                    currencyValue = anyCurrency.USD,
                                    date = getStartOfDay() ////Date(result.data.time_last_update_unix * 1000L)
                                )
                            )
                        }
                    }

                    Result.Success(true)
                    return result.toString()
                } else {
                    Result.Error(Constants.GENERIC_ERROR)
                    return "Ошибка"
                }
            }

            else -> {
                result as Result.Error
                return ""
            }
        }
    }

    suspend fun currencyList(): List<CurrencyEntity> {
        return currencyLocalDataSource.currencyList()
    }

    private fun getStartOfDay(): Date {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DATE]
        calendar[year, month, day, 0, 0] = 0
        return calendar.time
    }
}