package ru.arsmarks.githubclient.di.provider

import retrofit2.Retrofit
import ru.arsmarks.githubclient.data.SearchApi
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class ApiProvider(
    private val retrofit: Retrofit
) : Provider<SearchApi> {
    override fun get(): SearchApi = retrofit.create(SearchApi::class.java)
}