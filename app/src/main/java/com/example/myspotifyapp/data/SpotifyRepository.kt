package com.example.myspotifyapp.data

import com.example.myspotifyapp.data.model.Item
import com.example.myspotifyapp.data.model.ResponseSongDataModel
import com.example.myspotifyapp.data.network.SpotifyNetwork
import com.example.myspotifyapp.data.network.token.TokenNetwork

class SpotifyRepository {


    suspend fun getAllTracks():List<Item> {
        var tracksList= SpotifyNetwork().getAllTracks("ES")
        return tracksList.tracks.items
    }

    suspend fun getSong(id: String):ResponseSongDataModel {
        return SpotifyNetwork().getSong(id)
    }
}