package com.binar.challenge8.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.binar.challenge8.model.GetAllUserResponse
import com.binar.challenge8.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(private val repository: UserRepository) : ViewModel() {
    var userState : MutableState<GetAllUserResponse> = mutableStateOf(GetAllUserResponse("","","","","","",""))
    var username : MutableState<String> = mutableStateOf("dd")





    fun loginLive(email : String, password : String){
             repository.loginRepo(email, password, userState)

    }

    fun registerLive(email : String, password: String, username : String){
        repository.registerRepo(username, email, password)
    }




}