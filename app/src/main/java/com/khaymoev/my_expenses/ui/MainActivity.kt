package com.khaymoev.my_expenses.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.khaymoev.my_expenses.R
import com.khaymoev.my_expenses.common.NavigationHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), NavigationHost {

    private val viewModel: MainViewModel by viewModels()

    companion object {
        private val TOP_LEVEL_DESTINATION = setOf(
            R.id.navigation_expenses_list,
            R.id.navigation_categories_list,
            R.id.navigation_reports
        )
    }

    private lateinit var appConfiguration: AppBarConfiguration

    private var navController: NavController? = null
    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as? NavHostFragment
            ?: return
        navController = findNavController(R.id.mainNavHostFragment)
        appConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATION)

        navController?.apply {
            mainBottomNavView.setupWithNavController(this)
        }
    }

    /**
     * Регистрируем Toolbar внутри [MainActivity]
     *
     * @param toolbar виджет Toolbar приходящий из фрагментов
     */
    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        if (navController != null)
            toolbar.setupWithNavController(navController!!, appConfiguration)
    }
}