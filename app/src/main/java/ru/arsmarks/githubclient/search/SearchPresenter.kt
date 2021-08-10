package ru.arsmarks.githubclient.search

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.arsmarks.githubclient.domain.SearchReposUseCase
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import ru.arsmarks.githubclient.navigation.Screens
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val searchReposUseCase: SearchReposUseCase,
    private val router: Router
) : MvpPresenter<MvpSearchView>() {
    private val TAG = this::class.simpleName
    private val compositeDisposable = CompositeDisposable()

    fun searchRepos(name: String?) {
        if (name == null)
            return
        viewState.startLoading()
        compositeDisposable.add(
            searchReposUseCase(name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.stopLoading()
                    Log.d(TAG, "searchRepos: ${it.size}")
                    if (it.isEmpty()) {
                        viewState.showEmptyRepos()
                    } else {
                        viewState.showRepos(it)
                    }
                }, {
                    viewState.stopLoading()
                })
        )
    }

    fun openOverView(repository: Repository) {
        router.navigateTo(Screens.OverView(repository))
    }
}