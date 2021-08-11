package ru.arsmarks.githubclient.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.arsmarks.githubclient.data.mappers.EntityMapper
import ru.arsmarks.githubclient.data.mappers.FavoriteMapper
import ru.arsmarks.githubclient.data.mappers.SearchMapper
import ru.arsmarks.githubclient.data.persistence.dao.RepoDao
import ru.arsmarks.githubclient.data.remote.SearchApi
import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

class GitHubRepositoriesImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val searchMapper: SearchMapper,
    private val favoriteMapper: FavoriteMapper,
    private val entityMapper: EntityMapper,
    private val repoDao: RepoDao
) : GitHubRepositories {

    override fun searchRepos(name: String): Single<List<Repository>> {
        return searchApi.searchRepositories("$name in:name")
            .map {
                it.items.map { searchMapper.transform(it) }
            }
            .toObservable()
            .flatMap { t -> Observable.fromIterable(t) }
            .map { repository ->
                if (repoDao.isRowIsExist(repository.id)) {
                    repository.apply {
                        isFavorite = true
                    }
                } else
                    repository
            }.toList()
            .observeOn(Schedulers.io())
    }

    override fun getSavedRepos(): Flowable<List<Repository>> {
        return repoDao.getAll()
            .map { reposList ->
                reposList.map { favoriteMapper.transform(it) }
            }
    }

    override fun deleteRepository(repository: Repository): Completable {
        return Completable.fromAction {
            repoDao.delete(repository.id)
        }.subscribeOn(Schedulers.io())
    }


    override fun addRepository(repository: Repository): Completable {
        return repoDao.insert(entityMapper.transform(repository))
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                Completable.complete()
            }
    }
}

