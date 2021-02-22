package com.example.myspotifyapp.presentation.fragments.list

import com.example.myspotifyapp.data.model.Album
import java.io.Serializable

data class ListState(

        val albumList: List<Album> = listOf()

):Serializable
