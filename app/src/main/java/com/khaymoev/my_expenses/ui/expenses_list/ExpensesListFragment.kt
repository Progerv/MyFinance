package com.khaymoev.my_expenses.ui.expenses_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment
import com.khaymoev.my_expenses.data.local.database.CategoryWithExpenses
import com.khaymoev.my_expenses.databinding.FragmentExpensesListBinding
import com.khaymoev.my_expenses.utils.doOnChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpensesListFragment: MainNavigationFragment(), OnItemClickCallback {

    private val viewModel: ExpensesListViewModel by viewModels()
    private val binding: FragmentExpensesListBinding by viewBinding(FragmentExpensesListBinding::bind)
    private var expenseListAdapter = ExpensesListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myViewFragment = inflater.inflate(R.layout.fragment_expenses_list, container, false)
        observeViewModel()
        return myViewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Тестовые данные
        viewModel.addD()
        initializeViews()
        initButton()
    }

    private fun initButton() {
        binding.addNewExpense.setOnClickListener {
            findNavController().navigate(ExpensesListFragmentDirections.actionNavigationExpensesListToNavigationExpenseEdit())
        }
    }

    override fun initializeViews() {
        binding.expensesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = expenseListAdapter
        }
    }

    /**
     * Функция внутри которой выполняется подписка на обновление объектов LiveData
     */
    override fun observeViewModel() {
        /**
         * Подписываемся на обновление списка затрат,
         * при обновлении передаем данные в объект [ExpensesListAdapter].
         * Выполняем проверку списка на пустоту, и обновляем
         * значение внутри [ExpensesListViewModel].
         */
        viewModel.allCategoriesWithExpenses.doOnChange(this) {
            expenseListAdapter.updateList(viewModel.withDatabaseTableToListView(it))
        }
    }

    override fun onItemClick(id: Long, string: String) {
        showToast("Click to expense with id = $id и name = $string")
    }

    /**
     * Отображает на экране Toast
     *
     * @param message текст сообщения
     * @param duration время показа сообщения на экране
     */
    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

}