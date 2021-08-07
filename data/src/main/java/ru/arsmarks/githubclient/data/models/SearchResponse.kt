package ru.arsmarks.githubclient.data.models

import com.google.gson.annotations.SerializedName

class SearchResponse(
    val items: List<RepositoryData>
)

class RepositoryData(
    val id: Int,
    @SerializedName("full_name")
    val fullName: String
)