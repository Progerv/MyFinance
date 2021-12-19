package com.khaymoev.my_expenses.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Интерфейс [PreferenceStorage] содержит ключи SharedPreferences
 */

interface PreferenceStorage {
    var currencyDefault: String
}

/**
 * Класс [SharedPreferencesStorage] управляет SharedPreferences внутри приложения
 */
@Singleton
class SharedPreferencesStorage @Inject constructor(context: Context): PreferenceStorage {

    companion object {
        const val PREFERENCES_NAME = "PREFS"
        const val PREFERENCES_CURRENCY = "PREFS_DATA_CURRENCY"
    }

    private val preferences: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override var currencyDefault by StringPreference(preferences, PREFERENCES_CURRENCY, "RUB")
}

class StringPreference(
    private val preference: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String
): ReadWriteProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preference.value.getString(name, defaultValue) ?: ""
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preference.value.edit { putString(name, value) }
    }
}