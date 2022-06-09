@file:Suppress("ControlFlowWithEmptyBody")

package com.binar.challenge8.repository

import androidx.compose.runtime.MutableState
import com.binar.challenge8.api.ApiService
import com.binar.challenge8.model.GetAllUserResponse
import com.binar.challenge8.model.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class UserRepository  @Inject constructor(@Named("user") private val apiService: ApiService) {
     fun loginRepo(email : String, password : String, liveData : MutableState<GetAllUserResponse> )  {

        val call : Call<GetAllUserResponse> = apiService.loginUser(email, password)
         call.enqueue(object  : Callback<GetAllUserResponse> {
             override fun onResponse(
                 call: Call<GetAllUserResponse>,
                 response: Response<GetAllUserResponse>
             )  {
                 if (response.isSuccessful){
                     if (response.code().toString() == "404"){
                         liveData.value = GetAllUserResponse("","","","","","","")

                     }else{
                         liveData.value = response.body()!!
                     }

                 }else{
                     liveData.value = GetAllUserResponse("","","","","","","")

                 }
             }

             override fun onFailure(call: Call<GetAllUserResponse>, t: Throwable) {
                 liveData.value = GetAllUserResponse("","","","","","","")


             }

         })


    }
    fun registerRepo(username : String, email :String, password: String){
        val call : Call<ResponseRegister> = apiService.registerUser(email, password, username)
        call.enqueue(object : Callback<ResponseRegister>{
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}