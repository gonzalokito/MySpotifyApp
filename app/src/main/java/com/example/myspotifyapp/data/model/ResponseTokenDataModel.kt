package com.example.myspotifyapp.data.model

data class ResponseTokenDataModel(
        val access_token: String,
        val expires_in: Int,
        val scope: String,
        val token_type: String
)
