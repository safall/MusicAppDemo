package com.example.musicapp.ui.artist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.data.model.Artist
import com.example.musicapp.utils.AdapterCallback
import com.example.musicapp.utils.ImageUtils
import kotlinx.android.synthetic.main.row_artist_item.view.*

class ArtistViewHolder(view: View, private val callback: AdapterCallback) :
    RecyclerView.ViewHolder(view) {

    private val artistNameTextView: TextView = view.artist_name_text_view
    private val artistImageView: ImageView = view.artist_image_view

    private lateinit var data: Artist

    private val cardClickListener = View.OnClickListener {
        callback.onItemClicked(data)
    }

    fun set(artist: Artist) {
        data = artist
        artistNameTextView.text = data.name
        ImageUtils.loadCircularImage(data.picture_small, artistImageView)
        artistImageView.contentDescription = data.name
        itemView.setOnClickListener(cardClickListener)
    }
}