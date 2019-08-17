package com.example.musicapp.ui.albums.albumslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.musicapp.R
import com.example.musicapp.data.model.Album
import com.example.musicapp.utils.AdapterCallback

class AlbumListAdapter internal constructor(
    private val callback: AdapterCallback,
    private val artistName: String
) : PagedListAdapter<Album, AlbumViewHolder>(DATA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_album_item, parent, false), callback
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.set(getItem(position), artistName = artistName)
    }

    companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Album>() {


            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem == newItem

        }
    }

}