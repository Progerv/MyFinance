package com.khaymoev.my_expenses.ui.expenses_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.MainNavigationFragment
import com.khaymoev.my_expenses.databinding.FragmentExpensesListBinding
import com.khaymoev.my_expenses.ui.expenses_list.adapter.ExpensesListAdapter
import com.khaymoev.my_expenses.ui.expenses_list.adapter.OnItemClickCallback
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

    override fun observeViewModel() {
        viewModel.allCategoriesWithExpenses.doOnChange(this) {
            expenseListAdapter.updateList(viewModel.withDatabaseTableToListView(it))
        }
    }

    override fun onItemClick(id: Long, string: String) {
        super.showToast("Enter expense name: $string")
    }
}