package ru.arsmarks.githubclient.data.models

import com.google.gson.annotations.SerializedName

class RepositoryOwner(
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String
)