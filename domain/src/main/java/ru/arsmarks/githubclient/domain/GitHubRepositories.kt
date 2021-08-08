package ru.arsmarks.githubclient.domain

import io.reactivex.Observable
import io.reactivex.Single
import ru.arsmarks.githubclient.domain.domainEntity.Repository

interface GitHubRepositories {

    fun searchRepos(name: String): Single<List<Repository>>

    fun getSavedRepos(): Observable<List<Repository>>
}