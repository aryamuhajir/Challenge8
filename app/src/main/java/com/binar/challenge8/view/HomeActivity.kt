package com.binar.challenge8.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.binar.challenge8.R
import com.binar.challenge8.manager.UserManager
import com.binar.challenge8.model.Result
import com.binar.challenge8.view.ui.theme.Challenge8Theme
import com.binar.challenge8.viewmodel.ViewModelFilm
import com.binar.challenge8.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current
                    val movieViewModel : ViewModelFilm = viewModel(modelClass = ViewModelFilm::class.java)
                    val userViewModel : ViewModelUser = viewModel(modelClass = ViewModelUser::class.java)

                    val dataFilm by movieViewModel.dataState.collectAsState()
                    val userManager = UserManager(context = context)
                    val username = userViewModel.username.value
                    val scope = rememberCoroutineScope()


                    userManager.userNAME.asLiveData().observe(this@HomeActivity){
                            userViewModel.username.value = it
                    }



                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                            
                            Text(text = "Welcome,", fontSize = 22.sp)
                            Text(modifier = Modifier.padding(start = 10.dp),text = username, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(100.dp))

                            Image(painter = painterResource(id = R.drawable.ic_baseline_star_24), contentDescription = "gambar", modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            FavoriteActivity::class.java
                                        )
                                    )
                                })
                            Image(painter = painterResource(id = R.drawable.ic_baseline_account_circle_24), contentDescription = "gambar", modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .clickable {

                                    scope.launch {
                                        userManager.logout()
                                        userManager.setStatus("no")

                                    }
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                })
                            
                        }
                        Text(text = "Popular Movies", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 185.dp)){
                            if (dataFilm.isEmpty()){
                                item {

                                }
                            }

                            items(dataFilm){

                                Greeting4(it)

                            }
                        }


                    }
                }
            }
        }

    }

}

@Composable
fun Greeting4(film: Result) {
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)) {
        Card(shape = RoundedCornerShape(10.dp),  elevation = 10.dp, modifier = Modifier
            .width(180.dp)
            .height(300.dp)
            .clickable {

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("film", film)
                context.startActivity(intent)

            }) {
            Column(
                Modifier
                    .fillMaxSize()) {
                Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500/" + film.posterPath), contentDescription = "gambar")
                Column(modifier = Modifier
                    .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = film
                        .title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

            }

        }
        
        
    }
}

