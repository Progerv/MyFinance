package com.khaymoev.my_expenses.ui.expenses_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment
import com.khaymoev.my_expenses.databinding.FragmentExpensesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpensesListFragment: MainNavigationFragment() {

    private val viewModel: ExpensesListViewModel by viewModels()
    private val binding: FragmentExpensesListBinding by viewBinding(FragmentExpensesListBinding::bind)

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