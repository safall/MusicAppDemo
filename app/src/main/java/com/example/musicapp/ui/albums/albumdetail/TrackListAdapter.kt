package com.example.musicapp.ui.albums.albumdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.data.model.Track

class TrackListAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(DATA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_HOLDER_TYPE_HEADER) {
            return TrackHeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_track_header, parent, false)
            )
        }

        return TrackViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_track, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TrackViewHolder) {
            holder.set(getItem(position) as Track)
        }
        if (holder is TrackHeaderViewHolder) {
            holder.set(getItem(position) as Int)
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (getItem(position) is Int) {
            return VIEW_HOLDER_TYPE_HEADER
        }
        return VIEW_HOLDER_TYPE_ELEMENT
    }


    companion object {
        private const val VIEW_HOLDER_TYPE_HEADER = 0
        private const val VIEW_HOLDER_TYPE_ELEMENT = 1
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean =
                (oldItem as Track).id == (newItem as Track).id

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean =
                oldItem as Track == newItem as Track
        }
    }

}