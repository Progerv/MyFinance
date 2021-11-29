package com.khaymoev.my_expenses.ui.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment
import com.khaymoev.my_expenses.databinding.FragmentReportsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportsFragment: MainNavigationFragment() {

    private val viewModel: ReportsViewModel by viewModels()
    private val binding: FragmentReportsBinding by viewBinding(FragmentReportsBinding::bind)

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