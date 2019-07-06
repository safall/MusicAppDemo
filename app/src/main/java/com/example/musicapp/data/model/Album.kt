package com.example.musicapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Album(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val fans: Int,
    val genre_id: Int,
    val id: String,
    val link: String,
    val record_type: String,
    val release_date: String,
    val title: String,
    val tracklist: String,
    val type: String
) : Parcelable