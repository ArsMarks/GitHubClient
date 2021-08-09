package ru.arsmarks.githubclient.ui

import androidx.recyclerview.widget.DiffUtil
import ru.arsmarks.githubclient.domain.domainEntity.Repository

class RepositoryDiffUtils : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        TODO("Not yet implemented")
    }
}