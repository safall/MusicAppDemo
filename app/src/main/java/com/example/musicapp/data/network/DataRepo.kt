package com.example.musicapp.data.network

import com.example.musicapp.data.model.Album
import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.BaseResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DataRepo @Inject constructor(private val service: RemoteService){
    companion object {
        const val LIMIT = 50
    }

    fun searchArtists(offset: Int, query: String): Single<BaseResponse<List<Artist>>> {
        return service.searchArtists(query, offset, LIMIT)
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchAlbumsByArtist(offset: Int, artistId: String): Single<BaseResponse<List<Album>>> {
        return service.fetchAlbumsByArtist(artistId, offset, LIMIT)
            .observeOn(AndroidSchedulers.mainThread())
    }
}