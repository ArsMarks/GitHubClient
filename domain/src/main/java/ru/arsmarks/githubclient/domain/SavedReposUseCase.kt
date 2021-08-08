package ru.arsmarks.githubclient.domain

import io.reactivex.Observable
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

interface SavedReposUseCase {
    operator fun invoke(): Observable<List<Repository>>
}

class SavedRepositoryUseCaseImpl @Inject constructor(
    private val gitHubRepositories: GitHubRepositories
) : SavedReposUseCase {
    override fun invoke(): Observable<List<Repository>> {
        return gitHubRepositories.getSavedRepos()
    }
}