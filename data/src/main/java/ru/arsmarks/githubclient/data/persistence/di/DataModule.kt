package ru.arsmarks.githubclient.data.persistence.di

import ru.arsmarks.githubclient.data.persistence.AppDatabase
import ru.arsmarks.githubclient.data.persistence.dao.RepoDao
import toothpick.config.Module
import toothpick.ktp.binding.bind

class DataModule : Module() {
    init {
        bind<AppDatabase>()
            .toProvider(DatabaseProvider::class)
            .providesSingleton()

        bind<RepoDao>().toProvider(RepoDaoProvider::class)
    }
}