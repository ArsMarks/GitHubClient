package ru.arsmarks.githubclient.domain

import io.reactivex.Observable
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

interface GetFavoriteReposUseCase {
    operator fun invoke(): Observable<List<Repository>>
}

class GetFavoriteRepositoryUseCaseImpl @Inject constructor(
    private val gitHubRepositories: GitHubRepositories
) : GetFavoriteReposUseCase {
    override fun invoke(): Observable<List<Repository>> {
        return gitHubRepositories.getSavedRepos()
    }
}