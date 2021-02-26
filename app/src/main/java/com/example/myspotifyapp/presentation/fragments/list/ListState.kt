package com.example.myspotifyapp.presentation.fragments.list

import com.example.myspotifyapp.data.model.Item
import java.io.Serializable

data class ListState(

        val trackList: List<Item> = listOf()

) : Serializable
