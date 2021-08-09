package ru.arsmarks.githubclient.data

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

class GitHubRepositoriesImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val searchTransformer: SearchTransformer
) : GitHubRepositories {
    override fun searchRepos(name: String): Single<List<Repository>> {
        return searchApi.searchRepositories("$name in:name")
            .observeOn(Schedulers.io())
            .map {
                it.items.map {
                    searchTransformer.transform(it)
                }
            }
    }

    override fun getSavedRepos(): Observable<List<Repository>> {
        return Observable.just(listOf())
    }
}