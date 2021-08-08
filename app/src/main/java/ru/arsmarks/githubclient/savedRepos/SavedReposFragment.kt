package ru.arsmarks.githubclient.savedRepos

import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.arsmarks.githubclient.domain.domainEntity.Repository

class SavedReposFragment : MvpAppCompatFragment(), SavedReposView {

    @InjectPresenter
    lateinit var savedReposPresenter: SavedReposPresenter

    override fun startLoading() {
    }

    override fun showEmptyRepos() {
    }

    override fun showRepos(repos: List<Repository>) {
    }
}