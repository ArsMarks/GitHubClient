package ru.arsmarks.githubclient.savedRepos

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.arsmarks.githubclient.domain.GetFavoriteReposUseCase
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import javax.inject.Inject

@InjectViewState
class SavedReposPresenter @Inject constructor(
    private val getFavoriteReposUseCase: GetFavoriteReposUseCase
) : MvpPresenter<SavedReposView>() {
    private val TAG = this::class.simpleName
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        compositeDisposable.add(
            getFavoriteReposUseCase().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Repository>>() {
                    override fun onNext(response: List<Repository>) {
                        if (response.isEmpty()) {
                            viewState.showEmptyRepos()
                        } else {
                            viewState.showRepos(response)
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }

                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}