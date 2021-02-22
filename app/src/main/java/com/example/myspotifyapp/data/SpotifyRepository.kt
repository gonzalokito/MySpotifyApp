package com.example.myspotifyapp.data

import com.example.myspotifyapp.data.model.Item
import com.example.myspotifyapp.data.network.SpotifyNetwork

class SpotifyRepository {

    suspend fun getAllTracks():List<Item> {

        var tracksList= SpotifyNetwork().getAllTracks("ES")
        return tracksList.tracks.items
    }
}