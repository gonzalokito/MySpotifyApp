package com.example.myspotifyapp.data.network.token

import com.example.myspotifyapp.BuildConfig
import com.example.myspotifyapp.data.model.ResponseTokenDataModel
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class TokenNetwork {

    private lateinit var service: TokenService

    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://accounts.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .build()

        service = retrofit.create(TokenService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
                .connectTimeout(90L, TimeUnit.SECONDS)
                .readTimeout(90L, TimeUnit.SECONDS)
                .writeTimeout(90L, TimeUnit.SECONDS)

        //Create HTTP client
        val encodedString: String = Credentials.basic(BuildConfig.PUBLIC_KEY, BuildConfig.PRIVATE_KEY)
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Authorization", encodedString)
                    .build()
            chain.proceed(request)
        }


        // Logger Interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        //Imprime los logs en el logCat si el Build Variant esta en Debug
        loggerInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)


        return builder.build()
    }

    suspend fun getToken(id: String): ResponseTokenDataModel {
        loadRetrofit()
        return service.getToken(id)
    }
}