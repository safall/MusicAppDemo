package com.example.musicapp.ui.albums.albumslist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.data.model.Album
import com.example.musicapp.utils.AdapterCallback
import com.example.musicapp.utils.ImageUtils
import kotlinx.android.synthetic.main.row_album_item.view.*

class AlbumViewHolder(view: View, private val itemCallback: AdapterCallback) :
    RecyclerView.ViewHolder(view) {

    private val albumNameTextView: TextView = view.album_name_text_view
    private val artistNameTextView: TextView = view.artist_name_text_view
    private val albumImageView: ImageView = view.album_image_view

    private lateinit var data: Album

    private val cardClickListener = View.OnClickListener {
        itemCallback.onItemClicked(data)
    }

    fun set(artist: Album?, artistName: String) {
        artist?.let {
            data = it
        }
        artistNameTextView.text = artistName
        albumNameTextView.text = data.title
        ImageUtils.loadImage(data.cover_big, albumImageView)
        albumImageView.contentDescription = data.title
        itemView.setOnClickListener(cardClickListener)
    }
}