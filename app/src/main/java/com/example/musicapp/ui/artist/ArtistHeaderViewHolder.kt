package com.example.musicapp.ui.artist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import kotlinx.android.synthetic.main.row_item_track_header.view.*

class ArtistHeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val discInfoTextView: TextView = view.header_text_view

    fun set() {
        discInfoTextView.text = view.context.getString(R.string.artist)
    }
}