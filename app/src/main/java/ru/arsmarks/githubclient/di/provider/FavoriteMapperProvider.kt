package ru.arsmarks.githubclient.di.provider

import ru.arsmarks.githubclient.data.persistence.FavoriteMapper
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class FavoriteMapperProvider : Provider<FavoriteMapper> {
    override fun get(): FavoriteMapper {
        return FavoriteMapper()
    }
}