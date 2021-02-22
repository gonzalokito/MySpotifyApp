package com.example.myspotifyapp.data

import com.example.myspotifyapp.data.model.Album
import com.example.myspotifyapp.data.model.ResponseAlbumListDataModel
import com.example.myspotifyapp.data.network.SpotifyNetwork

class SpotifyRepository {

    suspend fun getAllAlbums():List<Album> {

        var albums= SpotifyNetwork().getAllAlbums("41MnTivkwTO3UUJ8DrqEJJ","ES")
        return albums.albums
    }
}