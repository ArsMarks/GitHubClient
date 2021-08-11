package ru.arsmarks.githubclient.data.remote.di.provider

import retrofit2.Retrofit
import ru.arsmarks.githubclient.data.remote.SearchApi
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
internal class ApiProvider(
    private val retrofit: Retrofit
) : Provider<SearchApi> {
    override fun get(): SearchApi = retrofit.create(SearchApi::class.java)
}