package com.khaymoev.my_expenses.ui.categories_list.category_edit

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.databinding.FragmentAddNewCategoryBinding
import com.khaymoev.my_expenses.utils.convertColorToHEX
import dagger.hilt.android.AndroidEntryPoint
import yuku.ambilwarna.AmbilWarnaDialog

@AndroidEntryPoint
class CategoryEditBottomFragment(): BottomSheetDialogFragment() {

    private val binding: FragmentAddNewCategoryBinding by viewBinding(FragmentAddNewCategoryBinding::bind)
    private val viewModel: CategoryEditViewModel by viewModels()
    var defaultColour: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new_category,container,false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultColour = Color.parseColor(resources.getString(R.color.defaultColour))
        initButtons()
    }

    private fun initButtons() {
        binding.addNewCategoryButton.setOnClickListener {
            viewModel.addNewCategory(
                binding.categoryItemTextView.text.toString(),
                defaultColour.convertColorToHEX()
            )
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
        binding.chooseColourButton.setOnClickListener {
            openColorPicker()
        }
    }

    private fun openColorPicker() {
        val colorPicker = AmbilWarnaDialog(context, defaultColour, object :
            AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog) {}
            override fun onOk(dialog: AmbilWarnaDialog, colour: Int) {
                defaultColour = colour
                binding.viewWithChosenColour.setBackgroundColor(defaultColour)
            }
        })
        colorPicker.show()
    }
}