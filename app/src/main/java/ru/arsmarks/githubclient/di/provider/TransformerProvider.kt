package ru.arsmarks.githubclient.di.provider

import ru.arsmarks.githubclient.data.SearchMapper
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class TransformerProvider : Provider<SearchMapper>{
    override fun get(): SearchMapper {
        return SearchMapper()
    }
}