package com.khaymoev.my_expenses.utils

import android.graphics.Color
import android.view.View
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.ui.reports.adapter.Statistic

object ChartHelper {
    fun setupPieChart(view: View, prodsList: ArrayList<Statistic>) {

        (view as? PieChart)?.let { pieChartDiagram ->

            pieChartDiagram.isDrawHoleEnabled = true
            pieChartDiagram.setUsePercentValues(true)
            pieChartDiagram.setEntryLabelColor(R.color.black)
            pieChartDiagram.centerText = "Статистика"
            pieChartDiagram.setCenterTextSize(12F)
            pieChartDiagram.description.isEnabled = false

            val l = pieChartDiagram.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(false)
            l.isEnabled = true

            val dataEntries = ArrayList<PieEntry>()
            val colors: ArrayList<Int> = ArrayList()

            prodsList.forEach {
                dataEntries.add(
                    PieEntry(
                        it.expenseAmount.toFloat()
                    )
                )
                colors.add(Color.parseColor(it.color))
            }

            val dataSet = PieDataSet(dataEntries, "")
            dataSet.colors = colors

            val data = PieData(dataSet)
            data.setDrawValues(true)
            data.setValueFormatter(PercentFormatter(pieChartDiagram))
            data.setValueTextSize(12f)
            data.setValueTextColor(R.color.black)

            pieChartDiagram.data = data
            pieChartDiagram.invalidate()
        }
    }
}