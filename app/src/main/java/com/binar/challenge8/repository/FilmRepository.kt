package com.binar.challenge8.repository

import com.binar.challenge8.api.ApiService
import com.binar.challenge8.model.GetAllFilmResponse
import javax.inject.Inject
import javax.inject.Named

class FilmRepository @Inject constructor(@Named("film") private val apiService: ApiService) {

    suspend fun getFilm() : GetAllFilmResponse{
        return apiService.getPopularMovie("4861681ff7acfad2199c40775e4ef5cb")
    }

}