package com.example.myspotifyapp.data.auth

import com.example.myspotifyapp.data.network.token.TokenService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route


class RefreshTokenAuthenticator: Authenticator {

    lateinit var service:TokenService


    //Cuando se produce un 401 se ejecuta esta llamada
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = refreshToken()
        return response.request.newBuilder().header("Authorization",token).build()
    }

    private fun refreshToken(): String {

        if(::service.isInitialized){
            val response= service.refreshToken("client_credentials")
            try{
                val token = response.execute().body()
                if(token != null)
                    return token.access_token
            }catch(e: Exception){
                return ""
            }
        }
        return ""
    }

}