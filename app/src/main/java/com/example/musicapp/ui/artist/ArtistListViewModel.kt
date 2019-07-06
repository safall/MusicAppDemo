package com.example.musicapp.ui.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.network.DataRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ArtistListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val subject: PublishSubject<String> = PublishSubject.create()
    lateinit var dataSource: DataRepo

    var isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<String> = MutableLiveData()

    val artistList: MutableLiveData<List<Artist>> = MutableLiveData()
    val artistList_: LiveData<List<Artist>>
        get() = artistList

    val loadMoreartistList: MutableLiveData<List<Artist>> = MutableLiveData()
    val loadMoreartistList_: LiveData<List<Artist>>
        get() = loadMoreartistList

    var dataSize: Int = 0
    val queryLiveData = MutableLiveData<String>()

    init {
        compositeDisposable.add(subscribeToSearch())
    }

    private fun subscribeToSearch(): Disposable {
        return subject
            .map { it.trim() }
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter {
                it.length > 2
            }
            .subscribe {
                search(it, 0)
                queryLiveData.postValue(it)
            }
    }

    fun search(query: String, offset: Int) {
        compositeDisposable.add(dataSource.searchArtists(offset, query)
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                if (result.data.isEmpty()) {
                    isEmpty.postValue(true)
                } else {
                    dataSize = result.data.size
                    artistList.postValue(result.data)
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