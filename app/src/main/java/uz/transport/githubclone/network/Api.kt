package uz.transport.githubclone.network

import dev.davlatov.githubproject.data.model.search.SearchRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import uz.transport.githubclone.model.UserResponse
import uz.transport.githubclone.utils.Constants

interface Api {
    @GET("search/users")
    @Headers("Authorization: token ${Constants.EXTRA_ACCESS_TOKEN}")
    fun getSearchUsers(@Query("q") q: String): Call<UserResponse>
}