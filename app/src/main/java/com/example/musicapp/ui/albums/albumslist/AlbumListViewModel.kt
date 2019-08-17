package com.example.musicapp.ui.albums.albumslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.musicapp.data.AlbumDataSourceFactory
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.DataRepo
import com.example.musicapp.data.network.RemoteService
import io.reactivex.disposables.CompositeDisposable

class AlbumListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    lateinit var dataSource: DataRepo
    lateinit var service: RemoteService
    var artistId: String = ""
    var artistName: String = ""
    var isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<String> = MutableLiveData()
    var albumList_: LiveData<PagedList<Album>>? = null
    private val config: PagedList.Config =
        PagedList.Config.Builder().setPageSize(10).setEnablePlaceholders(false).build()

    fun loadAlbums() {
        val sourceFactory = AlbumDataSourceFactory(artistId, compositeDisposable, service)
        albumList_ = LivePagedListBuilder<Int, Album>(sourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}