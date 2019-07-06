package com.example.musicapp.data.network

import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.BaseResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DataRepo @Inject constructor(private val service: RemoteService){

    fun searchArtists(offset: Int, query: String): Single<BaseResponse<List<Artist>>> {
        return service.searchArtists(query, offset)
            .observeOn(AndroidSchedulers.mainThread())
    }
}