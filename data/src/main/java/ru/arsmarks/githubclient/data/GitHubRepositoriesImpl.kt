package ru.arsmarks.githubclient.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.arsmarks.githubclient.data.persistence.EntityMapper
import ru.arsmarks.githubclient.data.persistence.FavoriteMapper
import ru.arsmarks.githubclient.data.persistence.dao.RepoDao
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
            .observeOn(Schedulers.io())
            .map {
                it.items.map {
                    searchMapper.transform(it)
                }
            }
    }

    override fun getSavedRepos(): Flowable<List<Repository>> {
        return repoDao.getAll()
            .map {
                it.map { favoriteMapper.transform(it) }
            }
    }

    override fun deleteRepository(repository: Repository): Completable =
        Single.fromCallable {
            repoDao.delete(entityMapper.transform(repository)) == 1
        }.flatMapCompletable {
            if (it) {
                Completable.complete()
            } else {
                Completable.error(RuntimeException("Persistence error"))
            }
        }


    override fun addRepository(repository: Repository): Completable =
        Single.fromCallable {
            repoDao.insert(entityMapper.transform(repository))
        }.flatMapCompletable {
            Completable.complete()
        }
}
