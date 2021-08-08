package ru.arsmarks.githubclient

import android.app.Application
import ru.arsmarks.githubclient.di.NetworkModule
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick
import toothpick.ktp.KTP

class BaseApp : Application() {
    private lateinit var scope: Scope

    override fun onCreate() {
        super.onCreate()
        Toothpick.openRootScope()
        initLog()
    }

    private fun initDi() {
        scope = KTP.openScope(this).installModules(
            NetworkModule()
        )
    }

    private fun initLog() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}