package com.khaymoev.my_expenses.ui.categories_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import kotlinx.android.synthetic.main.item_category_list.view.*

interface OnItemClickCallback {
    fun onItemClick(id: Long, string: String)
}

class CategoriesListAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<CategoriesListAdapter.CategoriesListViewHolder>() {

    private val categoriesList: MutableList<CategoryEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListViewHolder {
        return CategoriesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesListViewHolder, position: Int) {
        holder.bind(categoriesList[position], onItemClickCallback)
    }

    override fun getItemCount(): Int = categoriesList.size

    fun updateList(list: List<CategoryEntity>) {
        this.categoriesList.clear()
        this.categoriesList.addAll(list)
        notifyDataSetChanged()
    }

    class CategoriesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: CategoryEntity, onItemClickCallback: OnItemClickCallback) {

            itemView.categoryItemTextView.text = model.name

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(
                    model.id,
                    model.name
                )
            }
        }
    }
}