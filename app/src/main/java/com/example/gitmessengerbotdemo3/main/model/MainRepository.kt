package com.example.gitmessengerbotdemo3.main.model

import com.example.gitmessengerbotdemo3.common.remote.BaseResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getComments(): Flow<BaseResponse>
}
