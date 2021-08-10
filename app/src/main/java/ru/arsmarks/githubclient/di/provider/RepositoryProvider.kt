package ru.arsmarks.githubclient.di.provider

import ru.arsmarks.githubclient.data.GitHubRepositoriesImpl
import ru.arsmarks.githubclient.data.SearchApi
import ru.arsmarks.githubclient.data.SearchMapper
import ru.arsmarks.githubclient.domain.GitHubRepositories
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class RepositoryProvider constructor(
    private val searchApi: SearchApi,
    private val searchMapper: SearchMapper
) : Provider<GitHubRepositories> {
    override fun get(): GitHubRepositories {
        return GitHubRepositoriesImpl(searchApi, searchMapper)
    }
}