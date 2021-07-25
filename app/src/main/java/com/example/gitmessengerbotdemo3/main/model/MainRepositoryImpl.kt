package com.example.gitmessengerbotdemo3.main.model

import com.example.gitmessengerbotdemo3.common.remote.BaseResponse
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.await

class MainRepositoryImpl @Inject constructor(
    private val interceptor: HttpLoggingInterceptor,
    private val retrofit: Retrofit.Builder
) : MainRepository {

    private fun buildRetrofit() = retrofit
        .client(getInterceptor(interceptor))
        .build()
        .create(MainService::class.java)

    private fun getInterceptor(vararg interceptors: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        for (interceptor in interceptors) builder.addInterceptor(interceptor)
        return builder.build()
    }

    @ExperimentalCoroutinesApi
    override fun getComments() = callbackFlow {
        trySend(BaseResponse.Loading())
        try {
            trySend(BaseResponse.Success(buildRetrofit().getComments().await()))
        } catch (exception: HttpException) {
            trySend(BaseResponse.Error(exception))
        }
        awaitClose { close() }
    }
}
