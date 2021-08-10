package ru.arsmarks.githubclient.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.arsmarks.githubclient.MainFragment
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import ru.arsmarks.githubclient.overview.OverViewFragment
import ru.arsmarks.githubclient.savedRepos.SavedReposFragment
import ru.arsmarks.githubclient.search.SearchFragment

object Screens {
    object Main : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment =
            MainFragment()
    }

    object Search : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment =
            SearchFragment()
    }

    object Saved : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment =
            SavedReposFragment()
    }

    class OverView(private val repository: Repository) : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment =
            OverViewFragment.newInstance(repository)
    }
}