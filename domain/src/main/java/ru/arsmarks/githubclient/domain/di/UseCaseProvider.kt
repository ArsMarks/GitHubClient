package ru.arsmarks.githubclient.domain.di

import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.usecases.*
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class SearchUseCaseProvider constructor(
    private val gitHubRepositories: GitHubRepositories
) : Provider<SearchReposUseCase> {
    override fun get(): SearchReposUseCase {
        return SearchReposUseCaseImpl(gitHubRepositories)
    }
}

@InjectConstructor
class SavedUseCaseProvider constructor(
    private val gitHubRepositories: GitHubRepositories
) : Provider<GetFavoriteReposUseCase> {
    override fun get(): GetFavoriteReposUseCase {
        return GetFavoriteRepositoryUseCaseImpl(gitHubRepositories)
    }
}

@InjectConstructor
class FavoriteUseCaseProvider constructor(
    private val gitHubRepositories: GitHubRepositories
) : Provider<FavoriteUseCase> {
    override fun get(): FavoriteUseCase {
        return FavoriteRepoUseCaseImpl(gitHubRepositories)
    }
}