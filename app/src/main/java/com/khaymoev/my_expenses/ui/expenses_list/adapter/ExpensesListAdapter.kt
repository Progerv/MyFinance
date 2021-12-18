package com.khaymoev.my_expenses.ui.expenses_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.utils.refactorString
import kotlinx.android.synthetic.main.item_expense_list.view.*

interface OnItemClickCallback {
    fun onItemClick(id: Long, string: String)
}

class ExpensesListAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<ExpensesListAdapter.ExpensesListViewHolder>() {

    private val expensesList: MutableList<CategoriesWithExpensesForAdapter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesListViewHolder {
        return ExpensesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_expense_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExpensesListViewHolder, position: Int) {
        holder.bind(expensesList[position], onItemClickCallback)
    }

    override fun getItemCount(): Int = expensesList.size

    fun updateList(list: List<CategoriesWithExpensesForAdapter>) {
        this.expensesList.clear()
        this.expensesList.addAll(list)
        notifyDataSetChanged()
    }

    class ExpensesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            model: CategoriesWithExpensesForAdapter,
            onItemClickCallback: OnItemClickCallback
        ) {

            itemView.expenseItemTextView.text = model.name

            itemView.categoryItemTextView.text = model.nameCategory

            itemView.amountItemTextView.text = "${model.amount.refactorString()} ${model.currency}"

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(
                    model.idExpense,
                    model.name
                )
            }
        }
    }
}