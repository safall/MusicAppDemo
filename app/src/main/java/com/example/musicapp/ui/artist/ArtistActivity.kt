package com.example.musicapp.ui.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.musicapp.R
import kotlinx.android.synthetic.main.activity_main.*

class ArtistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() =
        NavHostFragment.findNavController(host_fragment).navigateUp()
}
