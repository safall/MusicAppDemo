package com.example.musicapp.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.musicapp.R
import com.example.musicapp.data.model.Artist
import com.example.musicapp.utils.AdapterCallback

class ArtistListAdapter internal constructor(
    private val callback: AdapterCallback
) : ListAdapter<Artist, ArtistViewHolder>(DATA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_artist_item, parent, false), callback
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.set(getItem(position))
    }

    companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem == newItem
        }
    }

}