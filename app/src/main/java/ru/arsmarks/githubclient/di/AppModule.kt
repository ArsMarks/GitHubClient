package ru.arsmarks.githubclient.di

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import ru.arsmarks.githubclient.data.mappers.SearchMapper
import ru.arsmarks.githubclient.data.mappers.EntityMapper
import ru.arsmarks.githubclient.data.mappers.FavoriteMapper
import ru.arsmarks.githubclient.data.EntityMapperProvider
import ru.arsmarks.githubclient.data.FavoriteMapperProvider
import ru.arsmarks.githubclient.data.RepositoryProvider
import ru.arsmarks.githubclient.data.SearchMapperProvider
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

        bind<SearchMapper>().toProvider(SearchMapperProvider::class)
        bind<FavoriteMapper>().toProvider(FavoriteMapperProvider::class)
        bind<EntityMapper>().toProvider(EntityMapperProvider::class)
    }
}