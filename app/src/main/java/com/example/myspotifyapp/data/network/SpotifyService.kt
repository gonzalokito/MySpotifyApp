package com.example.myspotifyapp.data.network

import com.example.myspotifyapp.data.model.ResponseAlbumListDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    @GET("/v1/albums")
    suspend fun getAllAlbums(@Query("ids") ids: String, @Query("market") market: String): ResponseAlbumListDataModel

}