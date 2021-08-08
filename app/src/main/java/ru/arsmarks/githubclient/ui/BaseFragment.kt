package ru.arsmarks.githubclient.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment
import ru.arsmarks.githubclient.di.DI
import toothpick.Scope
import toothpick.Toothpick

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) :
    MvpAppCompatFragment(contentLayoutId) {

    protected open val parentScopeName: String by lazy {
        (parentFragment as? BaseFragment)?.fragmentScopeName
            ?: DI.APP_SCOPE
    }

    private lateinit var fragmentScopeName: String
    protected lateinit var scope: Scope
        private set

    protected open fun installModules(scope: Scope) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentScopeName = "${javaClass.simpleName}_${hashCode()}"
        if (Toothpick.isScopeOpen(fragmentScopeName)) {
            scope = Toothpick.openScope(fragmentScopeName)
        } else {
            scope = Toothpick.openScopes(parentScopeName, fragmentScopeName)
            installModules(scope)
        }
        super.onCreate(savedInstanceState)
    }
}