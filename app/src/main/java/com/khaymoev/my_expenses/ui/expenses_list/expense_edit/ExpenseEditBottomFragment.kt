package com.khaymoev.my_expenses.ui.expenses_list.expense_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.databinding.FragmentAddNewExpenseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseEditBottomFragment() : BottomSheetDialogFragment() {

    private val binding: FragmentAddNewExpenseBinding by viewBinding(FragmentAddNewExpenseBinding::bind)
    private val viewModel: ExpenseEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setListCategories()
        return inflater.inflate(R.layout.fragment_add_new_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()

        initSpinner(listOf())

        fragmentTextUpdateObserver()

        viewModel.getUpdatedText()
    }

    private fun initButton() {
        binding.addNewExpenseButton.setOnClickListener {
            viewModel.addExpense(
                name = binding.textExpenseInputEditText.text.toString(),
                amount = binding.textAmountInputEditText.text.toString().toFloat(),
                nameCategory = binding.categoryTextInputEditText.text.toString()
            )
        }
    }

    private fun initSpinner(items: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.categoryExpenseEditText.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    // Observer is waiting for viewModel to update our UI
    private fun fragmentTextUpdateObserver() {
        viewModel.uiTextLiveData.observe(viewLifecycleOwner, Observer { updatedText ->
            initSpinner(updatedText)
        })
    }
}