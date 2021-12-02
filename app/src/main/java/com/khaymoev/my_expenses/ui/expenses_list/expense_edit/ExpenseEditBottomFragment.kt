package com.khaymoev.my_expenses.ui.expenses_list.expense_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.databinding.FragmentAddNewExpenseBinding

class ExpenseEditBottomFragment() : BottomSheetDialogFragment() {

    private val viewModel: ExpenseEditViewModel by viewModels()
    private val binding: FragmentAddNewExpenseBinding by viewBinding(FragmentAddNewExpenseBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_new_expense, container, false)
//        viewModel.allCategoriesList.doOnChanges {
//            listCategory = viewModel.getListCategory(it)
//        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initSpinner(items: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.categoryExpenseEditText.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

}