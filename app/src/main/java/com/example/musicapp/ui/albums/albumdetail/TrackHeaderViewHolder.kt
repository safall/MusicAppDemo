package com.example.musicapp.ui.albums.albumdetail

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import kotlinx.android.synthetic.main.row_item_track_header.view.*


class TrackHeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val discInfoTextView: TextView = view.disc_info_text_view

    fun set(discNumber: Int) {
        discInfoTextView.text = view.context.getString(R.string.volume, discNumber)
    }
}