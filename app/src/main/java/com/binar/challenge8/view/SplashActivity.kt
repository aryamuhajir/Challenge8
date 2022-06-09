package com.binar.challenge8.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import com.binar.challenge8.R
import com.binar.challenge8.manager.UserManager
import com.binar.challenge8.view.ui.theme.Challenge8Theme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                }
            }
        }
        val userManager = UserManager(this)
        Handler().postDelayed({
            userManager.userSTATUS.asLiveData().observe(this){
                if (it == "yes"){
                    runOnUiThread{
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }else{
                    runOnUiThread{
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }

        }, 3000)
    }
}

@Composable
fun Greeting2(name: String) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.ic_baseline_airplay_24), contentDescription = "gambar", modifier = Modifier.height(200.dp).width(200.dp))



    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Challenge8Theme {
        Greeting2("Android")
    }
}