package com.khaymoev.my_expenses.di

import android.content.Context
import com.khaymoev.my_expenses.api.ApiInterface
import com.khaymoev.my_expenses.data.local.database.ExpensesDatabase
import com.khaymoev.my_expenses.data.preferences.PreferenceStorage
import com.khaymoev.my_expenses.data.preferences.SharedPreferencesStorage
import com.khaymoev.my_expenses.utils.Constants
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Класс предоставляющий зависимости
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    /**
     * Функция предоставляющая зависимость от [ExpensesDatabase]
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExpensesDatabase {
        return ExpensesDatabase.buildDatabase(context)
    }

    /**
     * Функция предоставляющая зависимость от PreferenceStorage
     */
    @Provides
    @Singleton
    fun provideSharedPreferencesStorage(@ApplicationContext context: Context): PreferenceStorage = SharedPreferencesStorage(context)

    /**
     * Функция предоставляющая зависимость от OkHttp
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(ChuckInterceptor(context))
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Функция предоставляющая зависимость от Retrofit
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    /**
     * Функция предоставляющая зависимость от ApiInterface
     */
    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}