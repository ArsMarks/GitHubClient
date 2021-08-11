package ru.arsmarks.githubclient.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.arsmarks.githubclient.R
import ru.arsmarks.githubclient.databinding.FragmentSearchBinding
import ru.arsmarks.githubclient.domain.di.FavoriteUseCaseProvider
import ru.arsmarks.githubclient.domain.di.SearchUseCaseProvider
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import ru.arsmarks.githubclient.domain.usecases.FavoriteUseCase
import ru.arsmarks.githubclient.domain.usecases.SearchReposUseCase
import ru.arsmarks.githubclient.extensions.hide
import ru.arsmarks.githubclient.extensions.show
import ru.arsmarks.githubclient.ui.BaseFragment
import ru.arsmarks.githubclient.ui.ReposAdapter
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind

class SearchFragment : BaseFragment(R.layout.fragment_search), MvpSearchView,
    SearchView.OnQueryTextListener {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private var reposAdapter: ReposAdapter? = null
    override fun installModules(scope: Scope) {
        scope.installModules(object : Module() {
            init {
                bind<SearchReposUseCase>().toProvider(SearchUseCaseProvider::class)
                bind<FavoriteUseCase>().toProvider(FavoriteUseCaseProvider::class)
            }
        })
    }

    @InjectPresenter
    lateinit var searchReposPresenter: SearchPresenter

    @ProvidePresenter
    fun provideSearchPresenter(): SearchPresenter =
        scope.getInstance(SearchPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        configureSearchView()
        configureSwipeRefresh()
    }

    private fun configureSearchView() {
        binding.svRepos.apply {
            setOnQueryTextListener(this@SearchFragment)
            setOnCloseListener {
                reposAdapter?.submitList(emptyList())
                false
            }
        }
    }

    private fun configureSwipeRefresh() {
        binding.swipeRefreshLayout.apply {
            setDistanceToTriggerSync(300)
            setOnRefreshListener {
                searchReposPresenter.searchRepos(binding.svRepos.query.toString())
            }
        }
    }

    private fun initAdapter() {
        reposAdapter = ReposAdapter(
            object : ReposAdapter.RepositoryHolderListener {
                override fun onRepositoryClicked(repository: Repository) {
                    searchReposPresenter.openOverView(repository)
                }
            },
            object : ReposAdapter.FavoriteRepositoryListener {
                override fun onFavoriteClicked(repository: Repository) {
                    searchReposPresenter.favoriteRepo(repository)
                    reposAdapter?.notifyDataSetChanged()
                }
            }
        )
        binding.rvRepos.adapter = reposAdapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchReposPresenter.searchRepos(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText == null)
            reposAdapter?.submitList(emptyList())
        return true
    }

    override fun startLoading() {
        binding.pbSearchRepos.show()
        binding.rvRepos.hide()
    }

    override fun stopLoading() {
        binding.pbSearchRepos.hide()
        binding.rvRepos.show()
        if (binding.swipeRefreshLayout.isRefreshing)
            binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun showEmptyRepos() {
        reposAdapter?.submitList(emptyList())
    }

    override fun showRepos(repos: List<Repository>) {
        reposAdapter?.submitList(repos)
    }
}