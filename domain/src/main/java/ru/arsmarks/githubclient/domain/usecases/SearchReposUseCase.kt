package ru.arsmarks.githubclient.domain.usecases

import io.reactivex.Observable
import io.reactivex.Single
import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

interface SearchReposUseCase {
    operator fun invoke(name: String): Single<List<Repository>>
}

class SearchReposUseCaseImpl @Inject constructor(
    private val gitHubRepositories: GitHubRepositories
) : SearchReposUseCase {
    override fun invoke(name: String): Single<List<Repository>> {
        return gitHubRepositories.searchRepos(name)
    }
}
