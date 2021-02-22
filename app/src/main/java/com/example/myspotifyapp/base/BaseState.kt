package com.example.myspotifyapp.base

import java.io.Serializable

sealed class BaseState{

    data class Normal(val dataNormal: Serializable): BaseState(){

    }

    data class Error(val error: Throwable): BaseState(){

    }

    data class Loading(val dataLoading: BaseExtraData?= null): BaseState(){

    }

}

data class BaseExtraData(val type: Int, val extraData: Any? = null): Serializable
