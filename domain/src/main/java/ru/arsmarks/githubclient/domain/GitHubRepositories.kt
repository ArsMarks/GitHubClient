package ru.arsmarks.githubclient.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ru.arsmarks.githubclient.domain.domainEntity.Repository

interface GitHubRepositories {

    fun searchRepos(name: String): Single<List<Repository>>

    fun getSavedRepos(): Flowable<List<Repository>>

    fun deleteRepository(repository: Repository): Completable

    fun addRepository(repository: Repository): Completable
}