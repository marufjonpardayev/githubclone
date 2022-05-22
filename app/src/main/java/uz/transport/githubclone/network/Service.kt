package uz.transport.githubclone.network

import dev.davlatov.githubproject.data.model.search.SearchRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("search/users")
    fun getUserName(@Query("q") q: String): Call<SearchRepository>

    @GET("search/repositories")
    fun getRepositoryName(@Query("q") q: String): Call<SearchRepository>

}

