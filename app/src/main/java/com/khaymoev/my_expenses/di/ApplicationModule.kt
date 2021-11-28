package com.khaymoev.my_expenses.di

import android.content.Context
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Класс предоставляющий зависимости
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    /**
     * Функция предоставляющая зависимость от CoinsDatabase
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExpensesDatabase {
        return ExpensesDatabase.buildDatabase(context)
    }

    /**
     * Функция предоставляющая зависимость от PreferenceStorage
     */
//    @Provides
//    @Singleton
//    fun provideSharedPreferencesStorage(@ApplicationContext context: Context): PreferenceStorage = SharedPreferencesStorage(context)
}