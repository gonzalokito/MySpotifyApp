package com.example.myspotifyapp.data.network

import com.example.myspotifyapp.data.model.ResponseAlbumListDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface SpotifyService {

    @GET("/v1/albums")
    suspend fun getAllAlbums(@Path("ids") ids: String,@Path("market") market: String): ResponseAlbumListDataModel

}