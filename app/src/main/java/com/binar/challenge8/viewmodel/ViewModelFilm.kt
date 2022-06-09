package com.binar.challenge8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.challenge8.model.Result
import com.binar.challenge8.repository.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFilm @Inject constructor(private val repository: FilmRepository) : ViewModel() {
    private val filmState = MutableStateFlow(emptyList<Result>())

    val dataState : StateFlow<List<Result>>
        get() = filmState


    init {
        viewModelScope.launch {
            val film = repository.getFilm()
            filmState.value = film.results
        }
    }


}