package com.example.musicapp.ui.albums.albumdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.R
import com.example.musicapp.data.model.Album

class AlbumDetailActivity : AppCompatActivity() {
    companion object {
        const val ARG_ALBUM = "ARG_ALBUM"
        const val ARG_ARTIST_NAME = "ARG_ARTIST_NAME"

        fun createBundle(album: Album, artistName: String): Bundle {
            val bundle = Bundle()
            bundle.putParcelable(ARG_ALBUM, album)
            bundle.putString(ARG_ARTIST_NAME,artistName)
            return bundle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
    }
}
