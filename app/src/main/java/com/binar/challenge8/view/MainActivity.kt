package com.binar.challenge8.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.binar.challenge8.R
import com.binar.challenge8.manager.UserManager
import com.binar.challenge8.model.GetAllUserResponse
import com.binar.challenge8.ui.theme.Challenge8Theme
import com.binar.challenge8.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

                    val hasil = userViewModel.userState.value

                    Greeting(hasil, userViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(hasil: GetAllUserResponse, userViewModel: ViewModelUser) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userManager = UserManager(context = context)

    val scope = rememberCoroutineScope()

    userViewModel.loginLive(email, password)




    Column(horizontalAlignment = Alignment.CenterHorizontally ,modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Text(text = "Login", fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier
            .padding(top = 60.dp)
            .padding(start = 20.dp))
        Image(painter = painterResource(id = R.drawable.ic_baseline_airplay_24), contentDescription = "gambar", modifier = Modifier
            .padding(top = 50.dp)
            .width(120.dp)
            .height(120.dp))
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(value = email, onValueChange = { email = it },label = { Text("Masukan Username")})
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField( value = password, onValueChange = { password = it },label = { Text(" Masukan Password") },visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(120.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), shape = RoundedCornerShape(10.dp),onClick = {
            scope.launch {
                if (hasil.id.isBlank()){
                    Toast.makeText(context, "USERNAME ATAU PASSWORD SALAH", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, "Berhasil login! ${hasil.id}", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, HomeActivity::class.java))
                    userManager.setStatus("yes")
                    userManager.login(hasil.username, hasil.password, hasil.email, hasil.id)


                }
            }



        }) {
            Text(text = "Login", fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Belum punya akun?", modifier = Modifier.clickable {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        })



    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Challenge8Theme {

        //Greeting("Android")
    }
}