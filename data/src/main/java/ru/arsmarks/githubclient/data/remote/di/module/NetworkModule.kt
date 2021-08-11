package ru.arsmarks.githubclient.data.remote.di.module

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.arsmarks.githubclient.data.remote.SearchApi
import ru.arsmarks.githubclient.data.remote.di.provider.ApiProvider
import ru.arsmarks.githubclient.data.remote.di.provider.OkHttpProvider
import ru.arsmarks.githubclient.data.remote.di.provider.RetrofitProvider
import toothpick.config.Module
import toothpick.ktp.binding.bind

class NetworkModule : Module() {
    init {
        bind(Gson::class.java)
            .toInstance(Gson())

        bind<OkHttpClient>()
            .toProvider(OkHttpProvider::class)
            .providesSingleton()

        bind<Retrofit>()
            .toProvider(RetrofitProvider::class)
            .providesSingleton()

        bind<SearchApi>().toProvider(ApiProvider::class)
            .providesSingleton()
    }
}