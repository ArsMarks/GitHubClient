package ru.arsmarks.githubclient.domain.usecases

import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

interface SaveRepoUseCase {
    operator fun invoke(repository: Repository)
}

class SaveRepoUseCaseImpl @Inject constructor(
    private val gitHubRepositories: GitHubRepositories
){

}