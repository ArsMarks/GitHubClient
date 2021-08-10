package ru.arsmarks.githubclient.savedRepos

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import ru.arsmarks.githubclient.domain.usecases.FavoriteUseCase
import ru.arsmarks.githubclient.domain.usecases.GetFavoriteReposUseCase
import javax.inject.Inject

@InjectViewState
class SavedReposPresenter @Inject constructor(
    private val getFavoriteReposUseCase: GetFavoriteReposUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : MvpPresenter<SavedReposView>() {
    private val TAG = this::class.simpleName
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        compositeDisposable.add(
            getFavoriteReposUseCase().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            viewState.showEmptyRepos()
                        } else {
                            viewState.showRepos(it)
                        }
                    }, {

                    }
                )
        )
    }

    fun favoriteRepo(repository: Repository) {
        favoriteUseCase(repository)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}