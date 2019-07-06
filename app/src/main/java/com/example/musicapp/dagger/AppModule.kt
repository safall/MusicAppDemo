package com.example.musicapp.dagger

import com.example.musicapp.data.network.DataRepo
import com.example.musicapp.data.network.RemoteService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient, gson: Gson): RemoteService {
        return Retrofit.Builder()
            .baseUrl(RemoteService.ENDPOINT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataRepo(service: RemoteService): DataRepo = DataRepo(service)

}