package com.example.musicapp.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.data.model.Artist
import com.example.musicapp.ui.albums.albumdetail.TrackHeaderViewHolder
import com.example.musicapp.utils.AdapterCallback

class ArtistListAdapter internal constructor(
    private val callback: AdapterCallback
) : ListAdapter<Artist, RecyclerView.ViewHolder>(DATA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_HOLDER_TYPE_HEADER) {
            return ArtistHeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_track_header, parent, false)
            )
        }

        return ArtistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_artist_item, parent, false), callback
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ArtistViewHolder) {
            holder.set(getItem(position-1))
        }
        if (holder is ArtistHeaderViewHolder) {
            holder.set()
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_HOLDER_TYPE_HEADER
        }
        return VIEW_HOLDER_TYPE_ELEMENT
    }

    companion object {
        private const val VIEW_HOLDER_TYPE_HEADER = 0
        private const val VIEW_HOLDER_TYPE_ELEMENT = 1
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem == newItem
        }
    }

}