package ru.arsmarks.githubclient.di.provider

import ru.arsmarks.githubclient.data.SearchTransformer
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class TransformerProvider : Provider<SearchTransformer>{
    override fun get(): SearchTransformer {
        return SearchTransformer()
    }
}