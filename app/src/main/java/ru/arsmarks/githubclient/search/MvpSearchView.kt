package ru.arsmarks.githubclient.search

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.arsmarks.githubclient.domain.domainEntity.Repository

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MvpSearchView : MvpView {
    fun startLoading()
    fun stopLoading()
    fun showEmptyRepos()
    fun showRepos(repos: List<Repository>)
}