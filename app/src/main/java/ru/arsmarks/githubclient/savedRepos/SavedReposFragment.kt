package ru.arsmarks.githubclient.savedRepos

import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.arsmarks.githubclient.R
import ru.arsmarks.githubclient.databinding.FragmentSavedRepositoriesBinding
import ru.arsmarks.githubclient.di.provider.SavedUseCaseProvider
import ru.arsmarks.githubclient.domain.GetFavoriteReposUseCase
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import ru.arsmarks.githubclient.ui.BaseFragment
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind

class SavedReposFragment : BaseFragment(R.layout.fragment_saved_repositories),
    SavedReposView {
    private val binding by viewBinding(FragmentSavedRepositoriesBinding::bind)

    override fun installModules(scope: Scope) {
        scope.installModules(object : Module() {
            init {
                bind<GetFavoriteReposUseCase>().toProvider(SavedUseCaseProvider::class)
            }
        })
    }

    @InjectPresenter
    lateinit var savedReposPresenter: SavedReposPresenter

    @ProvidePresenter
    fun provideSavedReposPresenter(): SavedReposPresenter =
        scope.getInstance(SavedReposPresenter::class.java)

    override fun startLoading() {
    }

    override fun showEmptyRepos() {
    }

    override fun showRepos(repos: List<Repository>) {
    }
}