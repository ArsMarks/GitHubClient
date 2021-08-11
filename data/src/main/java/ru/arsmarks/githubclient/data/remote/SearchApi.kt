package ru.arsmarks.githubclient.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.arsmarks.githubclient.data.remote.models.SearchResponse

interface SearchApi {
    @GET("/search/repositories")
    fun searchRepositories(
        @Query("q") searchQuery: String
    ): Single<SearchResponse>
}