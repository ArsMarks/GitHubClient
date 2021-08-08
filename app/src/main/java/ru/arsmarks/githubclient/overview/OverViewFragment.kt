package ru.arsmarks.githubclient.overview

import moxy.MvpAppCompatFragment
import ru.arsmarks.githubclient.extensions.FragmentArgumentDelegate

class OverViewFragment : MvpAppCompatFragment() {
    private var repositoryId: Int by FragmentArgumentDelegate()

    companion object {
        fun newInstance(repositoryId: Int): OverViewFragment = OverViewFragment().apply {
            this.repositoryId = repositoryId
        }
    }
}