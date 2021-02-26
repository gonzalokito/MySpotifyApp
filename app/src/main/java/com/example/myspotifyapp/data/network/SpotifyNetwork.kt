package com.example.myspotifyapp.data.network

import com.example.myspotifyapp.BuildConfig
import com.example.myspotifyapp.data.auth.RefreshTokenAuthenticator
import com.example.myspotifyapp.data.model.ResponsePlayListDataModel
import com.example.myspotifyapp.data.model.ResponseSongDataModel
import com.example.myspotifyapp.data.network.token.TokenNetwork
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class SpotifyNetwork {

    private lateinit var service: SpotifyService

    private fun loadRetrofit(token: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient(token))
            .build()

        service = retrofit.create(SpotifyService::class.java)
    }

    private fun createHttpClient(token: String): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        //Create HTTP client

        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
            }

        // Logger Interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        //Imprime los logs en el logCat si el Build Variant esta en Debug
        loggerInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)

        builder.authenticator(RefreshTokenAuthenticator())
                .connectTimeout(90L, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)

        return builder.build()
    }

    suspend fun getAllTracks(market:String): ResponsePlayListDataModel {
        val token = TokenNetwork().getToken("client_credentials").access_token
        loadRetrofit(token)
        return service.getAllAlbums(market)
    }
    suspend fun getSong(id:String): ResponseSongDataModel {
        val token = TokenNetwork().getToken("client_credentials").access_token
        loadRetrofit(token)
        return service.getSong(id)
    }
}


