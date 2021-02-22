package com.example.myspotifyapp.data.network

import com.example.myspotifyapp.BuildConfig
import com.example.myspotifyapp.data.model.ResponseAlbumListDataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class SpotifyNetwork {

    lateinit var service: SpotifyService

    private fun loadRetrofit(){
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()

        service = retrofit.create(SpotifyService::class.java)
    }
    suspend fun getAllAlbums(ids:String,market:String): ResponseAlbumListDataModel {
        loadRetrofit()
        return service.getAllAlbums(ids,market)
    }

    private fun createHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .connectTimeout(90L, TimeUnit.SECONDS)
            .readTimeout(90L, TimeUnit.SECONDS)
            .writeTimeout(90L, TimeUnit.SECONDS)

        //Create HTTP client
        val accessToken = "BQAsdidYvnyEkjoNyuGMX5CsjXMFoEay9MSp9OjJatqU7uJ8c8GVyKJnFU3gR059Le7eUtSSMVFXVjthLWs"
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(request)
        }


        // Logger Interceptor
        val loggerInterceptor = HttpLoggingInterceptor()
        //Imprime los logs en el logCat si el Build Variant esta en Debug
        loggerInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggerInterceptor)


        return builder.build()
    }
}