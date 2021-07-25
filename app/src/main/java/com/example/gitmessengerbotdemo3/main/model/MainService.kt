package com.example.gitmessengerbotdemo3.main.model

import retrofit2.Call
import retrofit2.http.GET

interface MainService {
    @GET("/comments")
    fun getComments(): Call<CommentsBean>
}
