package ru.arsmarks.githubclient.data.models

import com.google.gson.annotations.SerializedName

class RepositoryData(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("forks")
    val forksCount: Int,
    @SerializedName("stargazers_count")
    val starsCount: Int,
    @SerializedName("created_at")
    val createdAt: String
)