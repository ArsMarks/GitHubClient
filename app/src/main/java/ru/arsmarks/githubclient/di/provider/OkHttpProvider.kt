package ru.arsmarks.githubclient.di.provider

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import toothpick.InjectConstructor
import javax.inject.Provider

private val httpLogLevel = HttpLoggingInterceptor.Level.BODY

@InjectConstructor
class OkHttpProvider : Provider<OkHttpClient> {
    override fun get(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = httpLogLevel })
        .build()
}