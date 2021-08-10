package ru.arsmarks.githubclient.di.provider

import ru.arsmarks.githubclient.domain.*
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