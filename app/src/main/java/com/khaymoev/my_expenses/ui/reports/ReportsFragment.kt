package com.khaymoev.my_expenses.ui.reports

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.khaymoev.my_expenses.ui.date_selecter.DatePickerFragment
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment
import com.khaymoev.my_expenses.databinding.FragmentReportsBinding
import com.khaymoev.my_expenses.ui.reports.adapter.Statistic
import com.khaymoev.my_expenses.ui.reports.adapter.StatisticAdapter
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
        setupPieChart()

        initStatisticList(arrayListOf<Statistic>())
        loadPieChartData(arrayListOf())
        initButton()
        //binding.pieChartDiagram
    }

    private fun setupPieChart() {
        binding.pieChartDiagram.isDrawHoleEnabled = true
        binding.pieChartDiagram.setUsePercentValues(true)
        binding.pieChartDiagram.setEntryLabelColor(R.color.black)
        binding.pieChartDiagram.centerText = "Статистика"
        binding.pieChartDiagram.setCenterTextSize(12F)
        binding.pieChartDiagram.description.isEnabled = false

        val l = binding.pieChartDiagram.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.isEnabled = true

    }

    private fun loadPieChartData(prodsList: ArrayList<Statistic>) {
        //var dataPieChart: ArrayList<Statistic> = arrayListOf()
        var dataPieChart: MutableList<PieEntry> = arrayListOf()
        prodsList.forEach {
            dataPieChart.add(
                PieEntry(
                    it.expenseAmount.toFloat()
                )
            )
        }

        val colors = arrayListOf<Int>()
        colors.add(R.color.green)
        colors.add(R.color.red)
        colors.add(R.color.teal_200)

        val dataSet = PieDataSet(dataPieChart, "Статистика затрат")
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(binding.pieChartDiagram))
        data.setValueTextSize(12f)
        data.setValueTextColor(R.color.black)

        binding.pieChartDiagram.data = data
        binding.pieChartDiagram.invalidate()

    }

    private fun initStatisticList(prodsList: ArrayList<Statistic>) {
        binding.statistic.adapter =
            StatisticAdapter(items = prodsList, ctx = requireActivity().applicationContext)
    }

    private fun initButton() {

        binding.dateFromView.setOnClickListener {

            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager = requireActivity().supportFragmentManager

            // we have to implement setFragmentResultListener
            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")
                    binding.dateFromView.setText(date)
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
                    binding.dateToView.setText(date)
                    dateTo =
                        Date(TimeUnit.MILLISECONDS.toMillis(bundle.getLong("SELECTED_DATE_LONG")))
                    observeViewModel()
                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
        }
    }
}