package ru.arsmarks.githubclient.domain.domainEntity

data class Repository(
    val id: Int,
    val fullName: String,
    val description: String,
    val avatarUrl: String,
    val forksCount: Int,
    val starCount: Int,
    val createdAt: Long,
    val isFavorite: Boolean
)