package ru.arsmarks.githubclient.search

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.arsmarks.githubclient.R
import ru.arsmarks.githubclient.databinding.FragmentSearchBinding
import ru.arsmarks.githubclient.ui.BaseFragment

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
}