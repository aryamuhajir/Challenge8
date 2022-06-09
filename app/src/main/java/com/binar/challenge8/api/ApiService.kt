package com.binar.challenge8.api

import com.binar.challenge8.model.GetAllFilmResponse
import com.binar.challenge8.model.GetAllUserResponse
import com.binar.challenge8.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String
    ): GetAllFilmResponse


    @POST("register.php/")
    @FormUrlEncoded
    fun registerUser(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("username") username :String
    ) : Call<ResponseRegister>

    @POST("login.php")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<GetAllUserResponse>

}