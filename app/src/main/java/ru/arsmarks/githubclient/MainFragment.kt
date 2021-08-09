package ru.arsmarks.githubclient

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.arsmarks.githubclient.databinding.FragmentMainBinding
import ru.arsmarks.githubclient.savedRepos.SavedReposFragment
import ru.arsmarks.githubclient.search.SearchFragment
import ru.arsmarks.githubclient.ui.BaseFragment

class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureTabs()
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