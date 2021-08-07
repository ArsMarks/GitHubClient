package ru.arsmarks.githubclient.data

import io.reactivex.Observable
import io.reactivex.Single
import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

class GitHubRepositoriesImpl @Inject constructor(
    private val searchApi: SearchApi
) : GitHubRepositories {
    override fun searchRepos(name: String): Single<List<Repository>> {
        TODO("Not yet implemented")
    }

    override fun getSavedRepos(): Observable<Repository> {
        TODO("Not yet implemented")
    }
}