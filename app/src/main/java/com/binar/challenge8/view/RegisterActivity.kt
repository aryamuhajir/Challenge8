package com.binar.challenge8.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.binar.challenge8.R
import com.binar.challenge8.view.ui.theme.Challenge8Theme
import com.binar.challenge8.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userViewModel : ViewModelUser = viewModel(modelClass = ViewModelUser::class.java)

                    Greeting3(userViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting3(user: ViewModelUser) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var konfirmasi by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Register", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Image(modifier = Modifier.width(120.dp).height(120.dp),painter = painterResource(id = R.drawable.ic_baseline_airplay_24 ), contentDescription = "gambmar")
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(value = username, onValueChange = { username = it },label = { Text("Masukkan Username")})
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = email, onValueChange = { email = it },label = { Text("Masukkan Email")})
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(visualTransformation = PasswordVisualTransformation() ,value = password, onValueChange = { password = it },label = { Text("Masukkan Password")})
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(visualTransformation = PasswordVisualTransformation() ,value = konfirmasi, onValueChange = { konfirmasi = it },label = { Text("Konfirmasi Password")})
        Spacer(modifier = Modifier.height(120.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), shape = RoundedCornerShape(10.dp),onClick = {
                if (username.isNotBlank() && password.isNotBlank() && email.isNotBlank() && konfirmasi.isNotBlank()){
                    if (password == konfirmasi){
                        user.registerLive(email, password, username)
                        Toast.makeText(context, "Berhasil daftar", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(context, "Konfirmasi password tidak sesuai", Toast.LENGTH_LONG).show()

                    }
                }else{
                    Toast.makeText(context, "Isi semua", Toast.LENGTH_LONG).show()

                }
        }) {
            Text(text = "Register", fontSize = 25.sp)
        }




    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    Challenge8Theme {
        //Greeting3("Android")
    }
}