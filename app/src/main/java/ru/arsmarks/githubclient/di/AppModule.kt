package ru.arsmarks.githubclient.di

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import ru.arsmarks.githubclient.data.SearchMapper
import ru.arsmarks.githubclient.data.persistence.EntityMapper
import ru.arsmarks.githubclient.data.persistence.FavoriteMapper
import ru.arsmarks.githubclient.di.provider.EntityMapperProvider
import ru.arsmarks.githubclient.di.provider.FavoriteMapperProvider
import ru.arsmarks.githubclient.di.provider.RepositoryProvider
import ru.arsmarks.githubclient.di.provider.TransformerProvider
import ru.arsmarks.githubclient.domain.GitHubRepositories
import toothpick.config.Module
import toothpick.ktp.binding.bind

class AppModule(context: Context) : Module() {
    init {
        //Global
        bind(Context::class.java).toInstance(context)
        //Navigation
        val cicerone = Cicerone.create(Router())
        bind<Router>().toInstance(cicerone.router)
        bind<NavigatorHolder>().toInstance(cicerone.getNavigatorHolder())

        bind<GitHubRepositories>().toProvider(RepositoryProvider::class)
        bind<SearchMapper>().toProvider(TransformerProvider::class)
        bind<FavoriteMapper>().toProvider(FavoriteMapperProvider::class)
        bind<EntityMapper>().toProvider(EntityMapperProvider::class)
    }
}