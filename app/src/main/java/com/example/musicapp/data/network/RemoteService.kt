package com.example.musicapp.data.network

import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    companion object {
        const val ENDPOINT = "https://api.deezer.com/"
    }

    @GET("search/artist?")
    fun searchArtists(@Query("q") query: String, @Query("index") index: Int, @Query("limit") limit: Int): Single<BaseResponse<List<Artist>>>
}