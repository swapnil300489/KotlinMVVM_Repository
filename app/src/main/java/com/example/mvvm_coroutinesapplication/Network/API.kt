package com.example.mvvm_coroutinesapplication.Network

import com.example.mvvm_coroutinesapplication.Model.Post
import retrofit2.http.GET

interface API {
    @GET("posts")
    suspend fun getPost():List<Post>
}