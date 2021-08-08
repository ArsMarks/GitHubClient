package ru.arsmarks.githubclient.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import ru.arsmarks.githubclient.di.provider.RepositoryProvider
import ru.arsmarks.githubclient.domain.*
import toothpick.config.Module
import toothpick.ktp.binding.bind

class AppModule : Module() {
    init {
        //Navigation
        val cicerone = Cicerone.create(Router())
        bind<Router>().toInstance(cicerone.router)
        bind<NavigatorHolder>().toInstance(cicerone.getNavigatorHolder())

        bind<GitHubRepositories>().toProvider(RepositoryProvider::class)
//        bind<SavedReposUseCase>().toClass<SavedRepositoryUseCaseImpl>()
//        bind<SearchReposUseCase>().toClass<SearchReposUseCaseImpl>()
    }
}