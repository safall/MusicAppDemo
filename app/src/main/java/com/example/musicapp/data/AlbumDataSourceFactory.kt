package com.example.musicapp.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.RemoteService
import io.reactivex.disposables.CompositeDisposable

class AlbumDataSourceFactory(
    private val artistId: String,
    private val compositeDisposable: CompositeDisposable,
    private val githubService: RemoteService)
    : DataSource.Factory<Int, Album>() {

    val usersDataSourceLiveData = MutableLiveData<AlbumDataSource>()

    override fun create(): DataSource<Int, Album> {
        val usersDataSource = AlbumDataSource(artistId, compositeDisposable, githubService)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

}