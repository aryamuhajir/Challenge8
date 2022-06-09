package com.binar.challenge8.di

import com.binar.challenge8.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkClient {
    private const val BASE_URL = "https://apiexample.surelabsid.com/"
    private const val FILM_URL = "https://api.themoviedb.org/"


    private  val logging : HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private  val clint = OkHttpClient.Builder().addInterceptor(logging).build()

    @Provides
    @Singleton
    @Named("user")
    fun provideRetrofitUser() : ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clint)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("film")
    fun provideRetrofitFilm() : ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(FILM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clint)
            .build()
        return retrofit.create(ApiService::class.java)
    }




}