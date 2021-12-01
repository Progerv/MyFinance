package com.khaymoev.my_expenses.ui.expenses_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.data.local.database.ExpenseEntity
import com.khaymoev.my_expenses.utils.refactorString
import kotlinx.android.synthetic.main.item_expense_list.view.*

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
class ExpensesListAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<ExpensesListAdapter.ExpensesListViewHolder>() {


    /**
     * Переменная хранящая список объектов [ExpenseEntity], по умолчанию содержит пустой список
     */
    private val expensesList: MutableList<CategoriesWithExpensesForAdapter> = mutableListOf()

    /**
     * Создает новый объет [ExpensesListViewHolder] каждый раз когда RecyclerView в этом нуждается.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesListViewHolder {
        return ExpensesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_expense_list, parent, false)
        )
    }

    /**
     * Принимает объект ViewHolder и устанавливает необходимые
     * данные для соответствующего элемента RecyclerView
     */
    override fun onBindViewHolder(holder: ExpensesListViewHolder, position: Int) {
        holder.bind(expensesList[position], onItemClickCallback)
    }

    /**
     * Возвращает общее количество элементов списка.
     */
    override fun getItemCount(): Int = expensesList.size

    /**
     * Функция обновляющая список затрат [expensesList] внутри RecyclerView
     *
     * @param list список объектов [ExpenseEntity]
     */
    fun updateList(list: List<CategoriesWithExpensesForAdapter>) {
        this.expensesList.clear() //очистка списка криптовалют
        this.expensesList.addAll(list) //заполнение списка новыми данными
        notifyDataSetChanged()  //обновление RecyclerView
    }

    class ExpensesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            model: CategoriesWithExpensesForAdapter,
            onItemClickCallback: OnItemClickCallback
        ) {
            //устаналвиваем имя затраты
            itemView.expenseItemTextView.text = model.name

            itemView.categoryItemTextView.text = model.nameCategory

            //добавляем символ валюты к сумме затраты
            itemView.amountItemTextView.text = model.amount.refactorString()

            //обрабатываем клик по элементу списка
            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(
                    model.idExpense,
                    model.name
                )
            }
        }
    }
}