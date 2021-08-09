package ru.arsmarks.githubclient.search

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.arsmarks.githubclient.R
import ru.arsmarks.githubclient.databinding.FragmentSearchBinding
import ru.arsmarks.githubclient.di.provider.SearchUseCaseProvider
import ru.arsmarks.githubclient.domain.SearchReposUseCase
import ru.arsmarks.githubclient.ui.BaseFragment
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind

class SearchFragment : BaseFragment(R.layout.fragment_search), SearchView {
    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun installModules(scope: Scope) {
        scope.installModules(object : Module() {
            init {
                bind<SearchReposUseCase>().toProvider(SearchUseCaseProvider::class)
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


    }
}