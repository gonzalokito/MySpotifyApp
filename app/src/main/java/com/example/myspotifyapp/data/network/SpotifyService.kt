package com.example.myspotifyapp.data.network

import com.example.myspotifyapp.data.model.ResponsePlayListDataModel
import com.example.myspotifyapp.data.model.ResponseSongDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    @GET("/v1/playlists/1mLsrthzLLOvDJNJfQtgzw")
    suspend fun getAllAlbums(@Query("market") market: String): ResponsePlayListDataModel

    @GET("/v1/tracks/{id}")
    suspend fun getSong(@Path("id") id: String): ResponseSongDataModel

}