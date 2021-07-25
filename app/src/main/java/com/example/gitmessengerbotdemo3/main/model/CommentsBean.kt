package com.example.gitmessengerbotdemo3.main.model

import com.google.gson.annotations.SerializedName

class CommentsBean : ArrayList<CommentsBean.Item>() {
    data class Item(
        @SerializedName("body")
        val body: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("postId")
        val postId: Int
    )
}