package uz.transport.githubclone.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.transport.githubclone.utils.Constants

object RetrofitClient {
    val retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.apiURL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val apiInstance: Api = retrofit.create(Api::class.java)
}