package com.khaymoev.my_expenses.ui.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khaymoev.my_expenses.ui.date_selecter.DatePickerFragment
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment
import com.khaymoev.my_expenses.databinding.FragmentReportsBinding
import com.khaymoev.my_expenses.ui.reports.adapter.Statistic
import com.khaymoev.my_expenses.ui.reports.adapter.StatisticAdapter
import com.khaymoev.my_expenses.utils.ChartHelper
import com.khaymoev.my_expenses.utils.doOnChange
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ReportsFragment : MainNavigationFragment() {

    private val viewModel: ReportsViewModel by viewModels()
    private val binding: FragmentReportsBinding by viewBinding(FragmentReportsBinding::bind)
    private var dateFrom: Date = Date(1L * 1000L)
    private var dateTo: Date = Date()

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
        initializeViews()
    }

    override fun observeViewModel() {
        viewModel.allCategoriesWithExpenses.doOnChange(this) {
            val listInfo = viewModel.withDatabaseTableToListView(it, dateFrom, dateTo)
            initStatisticList(listInfo)
            loadPieChartData(listInfo)
        }
    }

    override fun initializeViews() {

        initStatisticList(arrayListOf())
        loadPieChartData(arrayListOf())
        initButton()
    }

    private fun loadPieChartData(prodsList: ArrayList<Statistic>) {

        ChartHelper.setupPieChart(binding.pieChartDiagram, prodsList)

    }

    private fun initStatisticList(prodsList: ArrayList<Statistic>) {
        binding.statistic.adapter =
            StatisticAdapter(items = prodsList, ctx = requireActivity().applicationContext)
    }

    private fun initButton() {

        binding.dateFromView.setOnClickListener {

            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    binding.dateFromView.text = date
                    dateFrom =
                        Date(TimeUnit.MILLISECONDS.toMillis(bundle.getLong("SELECTED_DATE_LONG")))
                    observeViewModel()
                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }

        binding.dateToView.setOnClickListener {

            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    binding.dateToView.text = date
                    dateTo =
                        Date(TimeUnit.MILLISECONDS.toMillis(bundle.getLong("SELECTED_DATE_LONG")))
                    observeViewModel()
                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }
    }
}