package ru.arsmarks.githubclient.domain.usecases

import io.reactivex.Completable
import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

class FavoriteRepoUseCaseImpl @Inject constructor(
    private val gitHubRepositories: GitHubRepositories
) : FavoriteUseCase {
    override fun invoke(repository: Repository): Completable {
        return if (repository.isFavorite) {
            gitHubRepositories.deleteRepository(repository)
        } else {
            gitHubRepositories.addRepository(repository)
        }
    }
}

interface FavoriteUseCase {
    operator fun invoke(repository: Repository): Completable
}