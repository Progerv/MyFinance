package com.khaymoev.my_expenses.common

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.khaymoev.my_expenses.R

/**
 * Интерфейс описываюший общие функции которые будут использоваться в наших фрагментах
 */
interface InitViews {
    /**
     * Функция внутри которой будет производиться
     * инициализация всех View внутри фрагментов
     */
    fun initializeViews()

    /**
     * Функция внутри которой будет выполняться
     * подписка на обновление объектов LiveData
     */
    fun observeViewModel()
}

/**
 * Интерфейс для изменения Toolbar внутри MainActivity
 */
interface NavigationHost {
    fun registerToolbarWithNavigation(toolbar: Toolbar)
}

/**
 * [MainNavigationFragment] регистрирует панель инструментов из фрагмента с активностью
 */
abstract class MainNavigationFragment : Fragment(), InitViews {
    private var navigationHost: NavigationHost? = null
    private var fragmentView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationHost) {
            navigationHost = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentView = view
    }

    /**
     * Получаем Toolbar из View и передаем его в Activity
     */
    override fun onResume() {
        super.onResume()

        val host = navigationHost ?: return
        val mainToolbar: Toolbar = fragmentView?.findViewById(R.id.toolbar) ?: return

        mainToolbar.inflateMenu(R.menu.main_menu)
        mainToolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }

        host.registerToolbarWithNavigation(mainToolbar)
        setHasOptionsMenu(true)
    }

    override fun onPause() {
        super.onPause()

        navigationHost = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_main ->
                showToast(resources.getString(R.string.settings_message))
        }
        return false
    }

    fun showToast(textMessage: String) {
        Toast.makeText(activity, textMessage, Toast.LENGTH_SHORT).show()
    }
}