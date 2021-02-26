package com.example.myspotifyapp.data.network

import com.example.myspotifyapp.BuildConfig
import com.example.myspotifyapp.data.model.ResponsePlayListDataModel
import com.example.myspotifyapp.data.model.ResponseSongDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class SpotifyNetwork {

    private lateinit var service: SpotifyService
    private lateinit var mainToken: String

    private fun loadRetrofit() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient())
                .build()

        service = retrofit.create(SpotifyService::class.java)
    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
                .connectTimeout(90L, TimeUnit.SECONDS)
                .readTimeout(90L, TimeUnit.SECONDS)
                .writeTimeout(90L, TimeUnit.SECONDS)

        //Create HTTP client

        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $mainToken")
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

    suspend fun getAllTracks(market: String, token: String): ResponsePlayListDataModel {
        mainToken = token
        loadRetrofit()
        return service.getAllAlbums(market)
    }

    suspend fun getSong(id: String, token: String): ResponseSongDataModel {
        mainToken = token
        loadRetrofit()
        return service.getSong(id)
    }
}