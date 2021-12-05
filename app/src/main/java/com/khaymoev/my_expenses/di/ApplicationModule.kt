package com.khaymoev.my_expenses.di

import android.content.Context
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.preferences.PreferenceStorage
import com.khaymoev.my_expenses.data.preferences.SharedPreferencesStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Класс предоставляющий зависимости
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    private val applicationScope = CoroutineScope(SupervisorJob())
    /**
     * Функция предоставляющая зависимость от [ExpensesDatabase]
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExpensesDatabase {
        return ExpensesDatabase.buildDatabase(context, applicationScope)
    }

    /**
     * Функция предоставляющая зависимость от PreferenceStorage
     */
    @Provides
    @Singleton
    fun provideSharedPreferencesStorage(@ApplicationContext context: Context): PreferenceStorage = SharedPreferencesStorage(context)
}