package ru.arsmarks.githubclient

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.arsmarks.githubclient.data.RepositoryProvider
import ru.arsmarks.githubclient.databinding.FragmentMainBinding
import ru.arsmarks.githubclient.domain.GitHubRepositories
import ru.arsmarks.githubclient.savedRepos.SavedReposFragment
import ru.arsmarks.githubclient.search.SearchFragment
import ru.arsmarks.githubclient.ui.BaseFragment
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.binding.bind

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureTabs()
    }

    override fun installModules(scope: Scope) {
        scope.installModules(object : Module() {
            init {
                bind<GitHubRepositories>().toProvider(RepositoryProvider::class)
            }
        })
    }

    private fun configureTabs() {
        with(binding) {
            val viewPagerAdapter = ViewPagerAdapter(this@MainFragment).apply {
                addFragment(SearchFragment())
                addFragment(SavedReposFragment())
            }
            mainViewPager.adapter = viewPagerAdapter
            TabLayoutMediator(mainTabLayout, mainViewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Поиск"
                    }
                    1 -> {
                        tab.text = "Сохраненное"
                    }
                }
            }.attach()
        }
    }
}