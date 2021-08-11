package ru.arsmarks.githubclient.data.mappers

import ru.arsmarks.githubclient.data.persistence.entity.RepoEntity
import ru.arsmarks.githubclient.domain.domainEntity.Repository

class FavoriteMapper : BaseMapper<RepoEntity, Repository> {
    override fun transform(data: RepoEntity): Repository = with(data) {
        Repository(
            id = id,
            fullName = name,
            description = description,
            avatarUrl = "",
            forksCount = 1,
            starCount = 2,
            createdAt = 0,
            isFavorite = isFavorite
        )
    }
}

class EntityMapper : BaseMapper<Repository, RepoEntity> {
    override fun transform(data: Repository): RepoEntity = with(data) {
        RepoEntity(
            id = id,
            name = fullName,
            description = description,
            isFavorite = isFavorite,
            url = avatarUrl,
            userName = fullName
        )
    }
}