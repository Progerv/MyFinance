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
import com.khaymoev.my_expenses.utils.doOnChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportsFragment: MainNavigationFragment() {

    private val viewModel: ReportsViewModel by viewModels()
    private val binding: FragmentReportsBinding by viewBinding(FragmentReportsBinding::bind)

    override fun initializeViews() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reports, container, false)
        observeViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Statistic("Продукты",425.0), Statistic("Комунальные платежи", 3443.09)
        initStatisticList(arrayListOf<Statistic>())
    }

    override fun observeViewModel() {
        viewModel.allCategoriesWithExpenses.doOnChange(this) {
            initStatisticList(viewModel.withDatabaseTableToListView(it))
        }
    }

    private fun initStatisticList(prodsList: ArrayList<Statistic>) {
        binding.statistic.adapter = StatisticAdapter(items = prodsList, ctx = requireActivity().applicationContext)
    }
}