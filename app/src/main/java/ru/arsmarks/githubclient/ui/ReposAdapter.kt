package ru.arsmarks.githubclient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.arsmarks.githubclient.databinding.RepoItemBinding
import ru.arsmarks.githubclient.domain.domainEntity.Repository

class ReposAdapter(
    private val repositoryListener: RepositoryHolderListener,
    private val favoriteListener: FavoriteRepositoryListener
) :
    ListAdapter<Repository, ReposAdapter.RepositoryViewHolder>(RepositoryDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val repoItemBinding =
            RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(repoItemBinding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position), repositoryListener, favoriteListener)
    }

    inner class RepositoryViewHolder(private val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            repository: Repository,
            repositoryListener: RepositoryHolderListener,
            favoriteListener: FavoriteRepositoryListener
        ) {
            binding.root.setOnClickListener {
                repositoryListener.onRepositoryClicked(repository)
            }
            binding.textRepoName.text = repository.fullName
            binding.textRepoDescription.text = repository.description
            binding.imageFavoriteRepo.setOnClickListener {
                favoriteListener.onFavoriteClicked(repository)
            }
        }
    }

    interface RepositoryHolderListener {
        fun onRepositoryClicked(repository: Repository)
    }

    interface FavoriteRepositoryListener {
        fun onFavoriteClicked(repository: Repository)
    }
}