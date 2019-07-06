package com.example.musicapp.dagger

import com.example.musicapp.data.network.DataRepo
import com.example.musicapp.data.network.RemoteService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): RemoteService {
        return Retrofit.Builder()
            .baseUrl(RemoteService.ENDPOINT)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataRepo(service: RemoteService): DataRepo = DataRepo(service)

}