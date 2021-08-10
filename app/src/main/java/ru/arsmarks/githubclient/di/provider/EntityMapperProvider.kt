package ru.arsmarks.githubclient.di.provider

import ru.arsmarks.githubclient.data.persistence.EntityMapper
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class EntityMapperProvider : Provider<EntityMapper> {
    override fun get(): EntityMapper {
        return EntityMapper()
    }
}