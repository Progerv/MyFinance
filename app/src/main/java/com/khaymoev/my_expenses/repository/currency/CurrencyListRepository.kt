package com.khaymoev.my_expenses.repository.currency

import android.util.Log
import com.khaymoev.my_expenses.data.preferences.PreferenceStorage
import javax.inject.Inject
import com.khaymoev.my_expenses.api.Result
import com.khaymoev.my_expenses.api.successed
import com.khaymoev.my_expenses.data.local.database.CurrencyEntity
import com.khaymoev.my_expenses.utils.Constants

class CurrencyListRepository @Inject constructor(
    private val currencyListRemoteDataSource: CurrencyListRemoteDataSource,
    private val sharedPreferencesStorage: PreferenceStorage,
    private val currencyLocalDataSource: CurrencyLocalDataSource
) {

    suspend fun currencyList(): String {
        //Запрашиваем данные у удаленного источника данных и выполняем проверку результата
        when (val result = currencyListRemoteDataSource.currencyList(sharedPreferencesStorage.currencyDefault)) {
            is Result.Success -> {

                if (result.successed) {

                    Log.d("currencyList", result.data.toString())
                    result.data.let {

                        listCurr ->
                        val anyCurrency = listCurr.conversion_rates
                        Log.d("currencyList", anyCurrency.toString())

                        if (anyCurrency != null) {
                            currencyLocalDataSource.insertCurrencyIntoDatabase(
                                CurrencyEntity(
                                    currencyName = "RUB",
                                    currencyValue = anyCurrency.RUB.toDouble()
                                )
                            )

                            currencyLocalDataSource.insertCurrencyIntoDatabase(
                                CurrencyEntity(
                                    currencyName = "EUR",
                                    currencyValue = anyCurrency.EUR
                                )
                            )

                            currencyLocalDataSource.insertCurrencyIntoDatabase(
                                CurrencyEntity(
                                    currencyName = "USD",
                                    currencyValue = anyCurrency.USD
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
}