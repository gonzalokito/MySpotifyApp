package com.example.myspotifyapp.data.model

data class ResponseAlbumListDataModel(
    val albums: List<Album>
)

data class Album(
    val album_type: String,
    val artists: List<Artist>,
    val copyrights: List<Copyright>,
    val external_ids: ExternalIds,
    val external_urls: ExternalUrlsX,
    val genres: List<Any>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val label: String,
    val name: String,
    val popularity: Int,
    val release_date: String,
    val release_date_precision: String,
    val total_tracks: Int,
    val tracks: Tracks,
    val type: String,
    val uri: String
)

data class Artist(
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

data class Copyright(
    val text: String,
    val type: String
)

data class ExternalIds(
    val upc: String
)

data class ExternalUrlsX(
    val spotify: String
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int
)

data class Tracks(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)

data class ExternalUrls(
    val spotify: String
)

data class Item(
    val artists: List<ArtistX>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_urls: ExternalUrlsXXX,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val linked_from: LinkedFrom,
    val name: String,
    val preview_url: Any,
    val restrictions: Restrictions,
    val track_number: Int,
    val type: String,
    val uri: String
)

data class ArtistX(
    val external_urls: ExternalUrlsXX,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

data class ExternalUrlsXXX(
    val spotify: String
)

data class LinkedFrom(
    val external_urls: ExternalUrlsXXXX,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)

data class Restrictions(
    val reason: String
)

data class ExternalUrlsXX(
    val spotify: String
)

data class ExternalUrlsXXXX(
    val spotify: String
)