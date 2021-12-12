package com.khaymoev.my_expenses.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaymoev.my_expenses.repository.currency.CurrencyListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для класса [MainActivity] использовать не будем,
 * создаем для целостности приложения с точки зрения подхода
 */
@HiltViewModel
class MainViewModel @Inject constructor() :
    ViewModel()