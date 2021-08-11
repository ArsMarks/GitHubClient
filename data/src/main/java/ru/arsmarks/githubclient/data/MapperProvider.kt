package ru.arsmarks.githubclient.data

import ru.arsmarks.githubclient.data.mappers.SearchMapper
import ru.arsmarks.githubclient.data.mappers.EntityMapper
import ru.arsmarks.githubclient.data.mappers.FavoriteMapper
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class SearchMapperProvider : Provider<SearchMapper> {
    override fun get(): SearchMapper {
        return SearchMapper()
    }
}

@InjectConstructor
class FavoriteMapperProvider : Provider<FavoriteMapper> {
    override fun get(): FavoriteMapper {
        return FavoriteMapper()
    }
}

@InjectConstructor
class EntityMapperProvider : Provider<EntityMapper> {
    override fun get(): EntityMapper {
        return EntityMapper()
    }
}