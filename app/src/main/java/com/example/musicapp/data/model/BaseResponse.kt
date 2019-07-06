package com.example.musicapp.data.model

data class BaseResponse<T>(var data: T, var total: Int, var next: String?, var prev: String?)
