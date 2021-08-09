package ru.arsmarks.githubclient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.arsmarks.githubclient.databinding.RepoItemBinding
import ru.arsmarks.githubclient.domain.domainEntity.Repository

//TODO click listeners
class ReposAdapter(

) :
    ListAdapter<Repository, ReposAdapter.RepositoryViewHolder>(RepositoryDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RepositoryViewHolder(private val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository) {

        }
    }
}