package com.example.musicapp.utils

interface AdapterCallback {
    fun <T> onItemClicked(item: T)
}