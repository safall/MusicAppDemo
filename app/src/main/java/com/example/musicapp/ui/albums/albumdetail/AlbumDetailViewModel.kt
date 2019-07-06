package com.example.musicapp.ui.albums.albumdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.model.Track
import com.example.musicapp.data.network.DataRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumDetailViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    lateinit var dataSource: DataRepo
    lateinit var album: Album
    var artistName: String = ""
    var isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<String> = MutableLiveData()

    val trackList: MutableLiveData<List<Track>> = MutableLiveData()
    val trackList_: LiveData<List<Track>>
        get() = trackList

    fun loadAlbumTracks() {
        compositeDisposable.add(dataSource.fetchAlbumTracks(0, album.id)
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.data.isEmpty()) {
                    isEmpty.postValue(true)
                } else {
                    trackList.postValue(result.data)
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
