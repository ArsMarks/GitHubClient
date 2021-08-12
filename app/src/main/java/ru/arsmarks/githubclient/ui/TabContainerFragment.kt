package ru.arsmarks.githubclient.ui

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.arsmarks.githubclient.R
import ru.arsmarks.githubclient.navigation.BackButtonListener
import ru.arsmarks.githubclient.navigation.LocalCiceroneHolder
import ru.arsmarks.githubclient.navigation.RouterProvider
import ru.arsmarks.githubclient.navigation.Screens
import toothpick.Scope
import toothpick.config.Module
import javax.inject.Inject

class TabContainerFragment : BaseFragment(R.layout.fragment_tab_container), BackButtonListener {

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.fragmentContainer, childFragmentManager)
    }

    override fun installModules(scope: Scope) {
        (object : Module() {
            init {
                bind(LocalCiceroneHolder::class.java).toInstance(LocalCiceroneHolder())
            }
        })
    }

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    private val containerName: String
        get() = requireArguments().getString(EXTRA_NAME)!!

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder.getCicerone(containerName)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            when (containerName) {
                Screens.Search::class.simpleName ->{
                    cicerone.router.replaceScreen(Screens.Search)
                }
                Screens.Saved::class.simpleName ->{
                    cicerone.router.replaceScreen(Screens.Saved)
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.ftc_container)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            true
        } else {
            (activity as RouterProvider?)?.router?.exit()
            true
        }
    }

    companion object {
        private const val EXTRA_NAME = "tcf_extra_name"

        fun getNewInstance(name: String?) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                }
            }
    }
}