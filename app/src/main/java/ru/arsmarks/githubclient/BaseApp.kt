package ru.arsmarks.githubclient

import android.app.Application
import ru.arsmarks.githubclient.data.persistence.di.DataModule
import ru.arsmarks.githubclient.di.AppModule
import ru.arsmarks.githubclient.di.DI
import ru.arsmarks.githubclient.di.NetworkModule
import timber.log.Timber
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.ktp.KTP

class BaseApp : Application() {
    private lateinit var scope: Scope

    override fun onCreate() {
        super.onCreate()
        initDi()
        initLog()
    }

    private fun initDi() {
        scope = KTP.openScope(DI.APP_SCOPE).installModules(
            AppModule(this@BaseApp),
            NetworkModule(),
            DataModule()
        )
        if (BuildConfig.DEBUG)
            KTP.setConfiguration(Configuration.forDevelopment())
        else
            KTP.setConfiguration(Configuration.forProduction())
    }

    private fun initLog() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}