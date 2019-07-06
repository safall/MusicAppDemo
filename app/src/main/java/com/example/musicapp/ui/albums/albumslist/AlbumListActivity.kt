package com.example.musicapp.ui.albums.albumslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.musicapp.R
import kotlinx.android.synthetic.main.activity_album_list.*

class AlbumListActivity : AppCompatActivity() {
    companion object {
        const val ARG_ARTIST_ID = "ARG_ARTIST_ID"
        const val ARG_ARTIST_NAME = "ARG_ARTIST_NAME"


        fun createBundle(artistId: String, artistName: String): Bundle {
            val bundle = Bundle()
            bundle.putString(ARG_ARTIST_ID, artistId)
            bundle.putString(ARG_ARTIST_NAME, artistName)
            return bundle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.album)
    }

    override fun onSupportNavigateUp() =
        NavHostFragment.findNavController(host_fragment).navigateUp()
}