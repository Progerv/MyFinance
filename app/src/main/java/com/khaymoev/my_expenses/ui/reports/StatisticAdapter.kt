package com.khaymoev.my_expenses.ui.reports

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.khaymoev.my_expenses.R

class StatisticAdapter(items: ArrayList<Statistic>, ctx: Context) :
    ArrayAdapter<Statistic>(ctx, R.layout.item_statistic, items) {

    //view holder is used to prevent findViewById calls
    private class AttractionItemViewHolder {
        var category: TextView? = null
        var expenseAmount: TextView? = null
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view

        val viewHolder: AttractionItemViewHolder

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_statistic, viewGroup, false)

            viewHolder = AttractionItemViewHolder()
            viewHolder.category = view!!.findViewById<View>(R.id.category) as TextView
            viewHolder.expenseAmount = view.findViewById<View>(R.id.expenseAmount) as TextView

        } else {
            viewHolder = view.tag as AttractionItemViewHolder
        }

        val attraction = getItem(i)
        viewHolder.category!!.text = attraction!!.category
        viewHolder.expenseAmount!!.text = attraction.expenseAmount.toString()

        viewHolder.category!!.setOnClickListener {
            Toast.makeText(context, "Clicked category of " + attraction.category,
                Toast.LENGTH_SHORT).show()
        }

        view.tag = viewHolder

        return view
    }
}