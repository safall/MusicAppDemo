package com.example.musicapp.ui.albums.albumslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.DataRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    lateinit var dataSource: DataRepo
    var artistId: String = ""
    var artistName: String = ""
    var isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<String> = MutableLiveData()

    val albumList: MutableLiveData<List<Album>> = MutableLiveData()
    val albumList_: LiveData<List<Album>>
        get() = albumList

    fun loadAlbums() {
        compositeDisposable.add(dataSource.fetchAlbumsByArtist(0, artistId)
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.data.isEmpty()) {
                    isEmpty.postValue(true)
                } else {
                    albumList.postValue(result.data)
                }
            }) {
                showError.postValue(it.message)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}