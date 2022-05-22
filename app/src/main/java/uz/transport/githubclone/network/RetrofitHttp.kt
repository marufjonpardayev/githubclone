package uz.transport.githubclone.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitHttp {
    var accessString: String? = null
    private const val serverLink = "https://api.github.com/"

    private val client = getClient()
    private val retrofit = getRetrofit(client)

    private fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(serverLink)
            .client(client)
            .build()
    }

    private fun getClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Authorization", "token $accessString")
            chain.proceed(builder.build())
        }
        .build()

    val posterService: Service = retrofit.create(Service::class.java)
}