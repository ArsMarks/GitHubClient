package ru.arsmarks.githubclient.overview

import moxy.MvpAppCompatFragment
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import ru.arsmarks.githubclient.extensions.FragmentArgumentDelegate

class OverViewFragment : MvpAppCompatFragment() {
    private var repository: Repository by FragmentArgumentDelegate()

    companion object {
        fun newInstance(repository: Repository): OverViewFragment = OverViewFragment().apply {
            this.repository = repository
        }
    }
}