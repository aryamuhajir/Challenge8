package com.binar.challenge8.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.binar.challenge8.R
import com.binar.challenge8.model.Result
import com.binar.challenge8.view.ui.theme.Challenge8Theme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val data = intent.getParcelableExtra<Result>("film") as Result
                    val onClick = {}
                    Box(modifier = Modifier.fillMaxSize()){
                        Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
                            FloatingActionButton(onClick = onClick) {
                                Image(painter = painterResource(id = R.drawable.ic_baseline_star_border_24), contentDescription = "gambar", modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp))
                            }
                        }

                        Greeting7(film = data)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting7(film: Result) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .height(300.dp)) {
            Image(painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500/" + film.posterPath), contentDescription = "gambar", modifier = Modifier.width(180.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .background(color = Color.Gray)){
                Column(Modifier.fillMaxWidth()) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 80.dp),horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "${film.voteAverage}",fontSize = 40.sp,modifier = Modifier
                            .padding(bottom = 16.dp)
                            .padding(top = 20.dp), color = Color.White)
                        Image(painter = painterResource(id = R.drawable.ic_baseline_star_24), contentDescription = "GAMBAR", modifier = Modifier
                            .width(60.dp)
                            .height(60.dp))

                    }
                    Text(color = Color.White,text = "${film.voteCount} Votes",fontSize = 22.sp, modifier = Modifier.padding(start = 34.dp))


                }
            }



        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(15.dp)) {
            Text(text = "Title\t\t\t\t:  ${film.originalTitle}",fontSize = 22.sp, modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Language\t\t\t: ${film.originalLanguage}",fontSize = 22.sp,modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Release date\t\t\t: ${film.releaseDate}",fontSize = 18.sp)
            Text(text = "Overview",fontSize = 16.sp, fontWeight = FontWeight.Light, modifier = Modifier.padding(top = 15.dp))
            Text(text = film.overview,fontSize = 20.sp, textAlign = TextAlign.Justify)



        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    Challenge8Theme {
        //Greeting7("Android")
    }
}