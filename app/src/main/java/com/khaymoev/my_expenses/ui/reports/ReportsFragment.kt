package com.khaymoev.my_expenses.ui.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment

class ReportsFragment: MainNavigationFragment() {

    override fun initializeViews() {

    }

    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

}