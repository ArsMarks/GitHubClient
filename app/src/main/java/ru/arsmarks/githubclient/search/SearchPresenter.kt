package ru.arsmarks.githubclient.search

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.arsmarks.githubclient.domain.SearchReposUseCase
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val searchReposUseCase: SearchReposUseCase
) : MvpPresenter<SearchView>() {
    private val compositeDisposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        compositeDisposable.add(
            searchReposUseCase("test").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                })
        )
    }
}