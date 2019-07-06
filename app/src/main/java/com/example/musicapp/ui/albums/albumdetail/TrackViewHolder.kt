package com.example.musicapp.ui.albums.albumdetail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.data.model.Track
import kotlinx.android.synthetic.main.row_item_track.view.*

class TrackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val trackArtistTextView: TextView = view.track_artist_text_view
    private val trackTitleTextView: TextView = view.track_title_text_view
    private val trackNumberTextView: TextView = view.track_number_text_view

    fun set(track: Track) {
        trackArtistTextView.text = track.artist.name
        trackTitleTextView.text = track.title
        trackNumberTextView.text = track.track_position.toString()
    }
}