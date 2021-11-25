package com.khaymoev.my_expenses.ui.categories_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment

class CategoriesListFragment: MainNavigationFragment() {

    override fun initializeViews() {

    }

    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories_list, container, false)
    }

}