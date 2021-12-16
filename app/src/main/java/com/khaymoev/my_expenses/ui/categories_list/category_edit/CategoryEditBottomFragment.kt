package com.khaymoev.my_expenses.ui.categories_list.category_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.databinding.FragmentAddNewCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryEditBottomFragment(): BottomSheetDialogFragment() {

    private val binding: FragmentAddNewCategoryBinding by viewBinding(FragmentAddNewCategoryBinding::bind)
    private val viewModel: CategoryEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new_category,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtons()
    }

    private fun initButtons() {
        binding.addNewCategoryButton.setOnClickListener {
            viewModel.addNewCategory(
                binding.categoryItemTextView.text.toString()
            )
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }
}