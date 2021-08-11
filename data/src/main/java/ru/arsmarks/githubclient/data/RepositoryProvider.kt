package ru.arsmarks.githubclient.data

import ru.arsmarks.githubclient.data.mappers.EntityMapper
import ru.arsmarks.githubclient.data.mappers.FavoriteMapper
import ru.arsmarks.githubclient.data.mappers.SearchMapper
import ru.arsmarks.githubclient.data.persistence.dao.RepoDao
import ru.arsmarks.githubclient.data.remote.SearchApi
import ru.arsmarks.githubclient.domain.GitHubRepositories
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class RepositoryProvider constructor(
    private val searchApi: SearchApi,
    private val searchMapper: SearchMapper,
    private val favoriteMapper: FavoriteMapper,
    private val entityMapper: EntityMapper,
    private val repoDao: RepoDao
) : Provider<GitHubRepositories> {
    override fun get(): GitHubRepositories {
        return GitHubRepositoriesImpl(
            searchApi,
            searchMapper,
            favoriteMapper,
            entityMapper,
            repoDao
        )
    }
}