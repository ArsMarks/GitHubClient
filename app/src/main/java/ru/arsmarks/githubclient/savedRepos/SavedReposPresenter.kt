package ru.arsmarks.githubclient.savedRepos

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
            getFavoriteReposUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d(TAG, "saved list repository: ${it.size}");
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
        compositeDisposable.add(favoriteUseCase(repository).observeOn(AndroidSchedulers.mainThread())
            .subscribe {

            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}