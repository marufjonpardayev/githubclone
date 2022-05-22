package uz.transport.githubclone.network


import retrofit2.http.*
import uz.transport.githubclone.model.AccessToken
import uz.transport.githubclone.model.Repository
import uz.transport.githubclone.model.User
import uz.transport.githubclone.utils.Constants

interface GithubApi {

    companion object{
        const val BASE_URL = Constants.apiURL
    }

    @Headers("Accept: application/json")
    @POST(Constants.domainURL + "login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): AccessToken

    @Headers("Accept: application/json")
    @GET("user/repos")
    suspend fun getRepositories(
        @Header("authorization") token: String
    ): List<Repository>

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun getUserData(
        @Header("authorization") token: String
    ): User

}