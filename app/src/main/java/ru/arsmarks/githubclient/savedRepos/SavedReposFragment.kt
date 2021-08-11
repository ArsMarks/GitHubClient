package ru.arsmarks.githubclient.savedRepos

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.arsmarks.githubclient.R
import ru.arsmarks.githubclient.databinding.FragmentSavedRepositoriesBinding
import ru.arsmarks.githubclient.domain.di.FavoriteUseCaseProvider
import ru.arsmarks.githubclient.domain.di.SavedUseCaseProvider
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import ru.arsmarks.githubclient.domain.usecases.FavoriteUseCase
import ru.arsmarks.githubclient.domain.usecases.GetFavoriteReposUseCase
import ru.arsmarks.githubclient.ui.BaseFragment
import ru.arsmarks.githubclient.ui.ReposAdapter
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind

class SavedReposFragment : BaseFragment(R.layout.fragment_saved_repositories),
    SavedReposView {
    private val binding by viewBinding(FragmentSavedRepositoriesBinding::bind)
    private var reposAdapter: ReposAdapter? = null
    override fun installModules(scope: Scope) {
        scope.installModules(object : Module() {
            init {
                bind<GetFavoriteReposUseCase>().toProvider(SavedUseCaseProvider::class)
                bind<FavoriteUseCase>().toProvider(FavoriteUseCaseProvider::class)
            }
        })
    }

    @InjectPresenter
    lateinit var savedReposPresenter: SavedReposPresenter

    @ProvidePresenter
    fun provideSavedReposPresenter(): SavedReposPresenter =
        scope.getInstance(SavedReposPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureAdapter()
    }

    private fun configureAdapter() {
        reposAdapter = ReposAdapter(
            object : ReposAdapter.RepositoryHolderListener {
                override fun onRepositoryClicked(repository: Repository) {

                }
            },
            object : ReposAdapter.FavoriteRepositoryListener {
                override fun onFavoriteClicked(repository: Repository) {
                    savedReposPresenter.favoriteRepo(repository)
                }
            }
        )
        binding.rvFavoriteRepos.adapter = reposAdapter
    }

    override fun startLoading() {
    }

    override fun showEmptyRepos() {
        reposAdapter?.submitList(emptyList())
    }

    override fun showRepos(repos: List<Repository>) {
        reposAdapter?.submitList(repos)
    }
}