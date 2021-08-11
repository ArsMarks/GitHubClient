package ru.arsmarks.githubclient.data.remote.models

import com.google.gson.annotations.SerializedName

class RepositoryOwnerData(
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String
)