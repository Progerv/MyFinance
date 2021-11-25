package com.khaymoev.my_expenses.ui.expenses_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment

class ExpensesListFragment: MainNavigationFragment() {

    override fun initializeViews() {

    }

    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expenses_list, container, false)
    }
}