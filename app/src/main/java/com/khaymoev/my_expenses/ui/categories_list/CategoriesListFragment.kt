package com.khaymoev.my_expenses.ui.categories_list

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
import com.khaymoev.my_expenses.databinding.FragmentCategoriesListBinding
import com.khaymoev.my_expenses.utils.doOnChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesListFragment: MainNavigationFragment(), OnItemClickCallback {

    private val viewModel: CategoriesListViewModel by viewModels()
    private val binding: FragmentCategoriesListBinding by viewBinding(FragmentCategoriesListBinding::bind)
    private var categoriesListAdapter = CategoriesListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myViewFragment = inflater.inflate(R.layout.fragment_categories_list, container, false)
        observeViewModel()
        return myViewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initButton()
    }

    private fun initButton() {
        binding.addNewCategory.setOnClickListener {
            findNavController().navigate(CategoriesListFragmentDirections.actionNavigationCategoriesListToNavigationCategoryEdit())
        }
    }

    override fun initializeViews() {
        binding.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoriesListAdapter
        }
    }

    override fun observeViewModel() {

        viewModel.allCategoriesList.doOnChange(this) {
            categoriesListAdapter.updateList(it)
        }
    }

    override fun onItemClick(id: Long, string: String) {
        super.showToast("Enter category name: $string")
    }
}