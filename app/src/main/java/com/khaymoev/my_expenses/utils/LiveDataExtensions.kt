package com.khaymoev.my_expenses.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.khaymoev.my_expenses.data.local.database.CategoryEntity

fun <T> LiveData<T>.doOnChange(owner: LifecycleOwner, f: (T) -> Unit) {
    observe(owner, {
        f(it)
    })
}