package com.example.musicapp.data

import androidx.paging.PageKeyedDataSource
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.RemoteService
import io.reactivex.disposables.CompositeDisposable

class AlbumDataSource(
    private val artistId: String,
    private val compositeDisposable: CompositeDisposable,
    private val apiService: RemoteService
) : PageKeyedDataSource<Int, Album>() {

    var offset = 0
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        compositeDisposable.add(
            apiService.fetchAlbumsByArtist(artistId, 0)
                .subscribe({ data ->
                    // clear retry since last request succeeded
//            setRetry(null)
//            networkState.postValue(NetworkState.LOADED)
//            initialLoad.postValue(NetworkState.LOADED)
                    offset += data.data.size
                    callback.onResult(data.data, null, offset)
                }, { throwable ->
                    // keep a Completable for future retry
//            setRetry(Action { loadInitial(params, callback) })
//            val error = NetworkState.error(throwable.message)
//            // publish the error
//            networkState.postValue(error)
//            initialLoad.postValue(error)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        compositeDisposable.add(
            apiService.fetchAlbumsByArtist(artistId, params.key)
                .subscribe({ data ->
                    // clear retry since last request succeeded
//            setRetry(null)
//            networkState.postValue(NetworkState.LOADED)
                    offset += data.data.size

                    callback.onResult(data.data, offset)
                }, { throwable ->
                    // keep a Completable for future retry
//            setRetry(Action { loadAfter(params, callback) })
//            // publish the error
//            networkState.postValue(NetworkState.error(throwable.message))
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}