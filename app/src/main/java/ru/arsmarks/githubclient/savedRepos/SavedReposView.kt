package ru.arsmarks.githubclient.savedRepos

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.arsmarks.githubclient.domain.domainEntity.Repository

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface SavedReposView : MvpView {
    fun startLoading()
    fun showEmptyRepos()
    fun showRepos(repos: List<Repository>)
}