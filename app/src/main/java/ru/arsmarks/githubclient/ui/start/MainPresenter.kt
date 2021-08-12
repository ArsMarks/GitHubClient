package ru.arsmarks.githubclient.ui.start

import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(
    private val router: Router
) : MvpPresenter<BottomNavigationView>() {

    fun onBackPressed() {
        router.exit()
    }
}