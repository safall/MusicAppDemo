package com.example.musicapp.dagger

import com.example.musicapp.ui.artist.ArtistListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeArtistListFragment(): ArtistListFragment
}