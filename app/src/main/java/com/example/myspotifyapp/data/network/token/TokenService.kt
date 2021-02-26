package com.example.myspotifyapp.data.network.token

import com.example.myspotifyapp.data.model.ResponseTokenDataModel
import retrofit2.Call
import retrofit2.http.*

interface TokenService {

    @FormUrlEncoded
    @POST("/api/token")
    suspend fun getToken(@Field("grant_type") grant_type: String): ResponseTokenDataModel

    @FormUrlEncoded
    @POST("/api/token")
    fun refreshToken(@Field("grant_type") grant_type: String): Call<ResponseTokenDataModel>

}