package ru.arsmarks.githubclient.data

import ru.arsmarks.githubclient.data.models.RepositoryData
import ru.arsmarks.githubclient.domain.domainEntity.Repository
import java.text.SimpleDateFormat
import java.util.*

class SearchMapper : BaseMapper<RepositoryData, Repository> {
    override fun transform(data: RepositoryData): Repository = with(data) {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date = sdf.parse(createdAt)
        val millis: Long = date.time
        Repository(
            id = id,
            name = name,
            fullName = fullName,
            description = description,
            avatarUrl = owner.avatarUrl,
            forksCount = forksCount,
            starCount = starsCount,
            createdAt = millis
        )
    }
}