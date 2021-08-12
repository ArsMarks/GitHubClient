package ru.arsmarks.githubclient.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.arsmarks.githubclient.R
import ru.arsmarks.githubclient.databinding.ActivityMainBinding
import ru.arsmarks.githubclient.navigation.BackButtonListener
import ru.arsmarks.githubclient.navigation.NavigationKeys
import ru.arsmarks.githubclient.navigation.Screens
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), BottomNavigationView {
    private val binding by viewBinding(ActivityMainBinding::bind)

    private val router by inject<Router>()
    private val navigatorHolder by inject<NavigatorHolder>()
    private val navigator = AppNavigator(activity = this, containerId = R.id.fragmentContainer)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter() = MainPresenter(router)
    private var currentTab: String = NavigationKeys.SEARCH_TAB_FRAGMENT

    companion object {
        const val currentTabKey = "CURRENT_TAB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope().inject(this)

        setupNavigationBar()

        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.navigation_search
        }
    }

    private fun setupNavigationBar() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    router.replaceScreen(Screens.Search)
                }
                R.id.navigation_favorite -> {
                    router.replaceScreen(Screens.Saved)
                }
            }
            true
        }
    }
    private fun selectTab(tab: String) {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fm.findFragmentByTag(tab)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return
        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.fragmentContainer,
                Screens.Tab(tab).createFragment(fm.fragmentFactory), tab
            )
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator = navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            presenter.onBackPressed()
        }
    }
}