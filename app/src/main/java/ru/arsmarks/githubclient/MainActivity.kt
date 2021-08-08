package ru.arsmarks.githubclient

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import ru.arsmarks.githubclient.navigation.Screens
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main) {
    private val router by inject<Router>()
    private val navigatorHolder by inject<NavigatorHolder>()
    private val navigator = AppNavigator(activity = this, containerId = R.id.mainFragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KTP.openRootScope().inject(this)

        if (savedInstanceState == null) {
            router.newRootScreen(Screens.Main)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator = navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}