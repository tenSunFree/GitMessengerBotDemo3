package com.example.gitmessengerbotdemo3.main.model

import androidx.annotation.DrawableRes

object MainTab {
    const val Home = 0
    const val Meeting = 1
    const val Record = 2
}

data class BottomBarBean(
    val title: String = "",
    @DrawableRes val icon: Int? = null,
    val id: Int = 0
)