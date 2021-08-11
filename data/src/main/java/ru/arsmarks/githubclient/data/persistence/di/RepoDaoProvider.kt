package ru.arsmarks.githubclient.data.persistence.di

import ru.arsmarks.githubclient.data.persistence.AppDatabase
import ru.arsmarks.githubclient.data.persistence.dao.RepoDao
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
internal class RepoDaoProvider(
    private val appDatabase: AppDatabase
) : Provider<RepoDao> {
    override fun get(): RepoDao {
        return appDatabase.repoDao()
    }
}