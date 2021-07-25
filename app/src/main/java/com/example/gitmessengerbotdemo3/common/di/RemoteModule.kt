package com.example.gitmessengerbotdemo3.common.di

import com.example.gitmessengerbotdemo3.main.model.MainRepository
import com.example.gitmessengerbotdemo3.main.model.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private const val BaseUrl = "https://jsonplaceholder.typicode.com"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideRepository(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        retrofit: Retrofit.Builder
    ): MainRepository = MainRepositoryImpl(httpLoggingInterceptor, retrofit)
}
