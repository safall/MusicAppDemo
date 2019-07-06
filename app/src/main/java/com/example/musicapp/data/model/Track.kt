package com.example.musicapp.data.model

data class Track(
    val artist: Artist,
    val disk_number: Int,
    val duration: String,
    val explicit_content_cover: Int,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    val id: String,
    val isrc: String,
    val link: String,
    val preview: String,
    val rank: String,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String,
    val track_position: Int,
    val type: String
)