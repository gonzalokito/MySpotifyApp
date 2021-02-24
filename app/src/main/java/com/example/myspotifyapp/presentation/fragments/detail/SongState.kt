package com.example.myspotifyapp.presentation.fragments.detail

import com.example.myspotifyapp.data.model.ResponseSongDataModel
import com.example.myspotifyapp.data.model.Track
import java.io.Serializable

data class SongState(

        val track: ResponseSongDataModel? = null

):Serializable
