package ru.arsmarks.githubclient.domain.usecases

import io.reactivex.Flowable
import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

interface GetFavoriteReposUseCase {
    operator fun invoke(): Flowable<List<Repository>>
}

class GetFavoriteRepositoryUseCaseImpl @Inject constructor(
    private val gitHubRepositories: GitHubRepositories
) : GetFavoriteReposUseCase {
    override fun invoke(): Flowable<List<Repository>> {
        return gitHubRepositories.getSavedRepos()
    }
}