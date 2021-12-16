package com.khaymoev.my_expenses.ui.categories_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.data.local.database.entities.CategoryEntity
import kotlinx.android.synthetic.main.item_category_list.view.*

/**
 * Интерфейс для получения информации о совершении клика по элементу
 * из списка
 */
interface OnItemClickCallback {
    /**
     * Функция передающая клик по элементу
     *
     * @param id индентификатор затраты
     */
    fun onItemClick(id: Long, string: String)
}

/**
 * Класс адаптер для отображения списка затрат в RecyclerView
 *
 * @param onItemClickCallback интерфейс для обработки кликов [OnItemClickCallback]
 */
class CategoriesListAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<CategoriesListAdapter.CategoriesListViewHolder>() {

    /**
     * Переменная хранящая список объектов [CategoryEntity], по умолчанию содержит пустой список
     */
    private val categoriesList: MutableList<CategoryEntity> = mutableListOf()

    /**
     * Создает новый объет [CategoriesListViewHolder] каждый раз когда RecyclerView в этом нуждается.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListViewHolder {
        return CategoriesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category_list, parent, false)
        )
    }

    /**
     * Принимает объект ViewHolder и устанавливает необходимые
     * данные для соответствующего элемента RecyclerView
     */
    override fun onBindViewHolder(holder: CategoriesListViewHolder, position: Int) {
        holder.bind(categoriesList[position], onItemClickCallback)
    }

    /**
     * Возвращает общее количество элементов списка.
     */
    override fun getItemCount(): Int = categoriesList.size

    /**
     * Функция обновляющая список категорий [categoriesList] внутри RecyclerView
     *
     * @param list список объектов [CategoryEntity]
     */
    fun updateList(list: List<CategoryEntity>) {
        this.categoriesList.clear()
        this.categoriesList.addAll(list) //заполнение списка новыми данными
        notifyDataSetChanged()  //обновление RecyclerView
    }

    class CategoriesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: CategoryEntity, onItemClickCallback: OnItemClickCallback) {

            //устаналвиваем имя затраты
            itemView.categoryItemTextView.text = model.name

            //обрабатываем клик по элементу списка
            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(
                    model.id,
                    model.name
                )
            }
        }
    }
}