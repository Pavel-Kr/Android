package com.example.lab1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab1.ui.theme.Lab1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1Theme {
                MainLayout(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    news: SingleNews,
    onLikeClicked: () -> Unit
    ){
    Column {
        Text(text = news.newsText,
            modifier = Modifier
                .padding(16.dp)
                .weight(9f)
        )
        Button(onClick = onLikeClicked,
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
                Text(text = "Likes: ${news.likes.value}",
                    style = TextStyle(fontSize = 14.sp)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsCardPreview(){
    Lab1Theme {
        NewsCard(news = SingleNews("Hello!"), onLikeClicked = {})
    }
}

@Composable
fun MainLayout(
    modifier: Modifier = Modifier,
    newsViewModel: NewsViewModel = viewModel()
){
    val uiState by newsViewModel.uiState.collectAsState()
    Column {
        Row(modifier = Modifier.weight(1f)){
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.Gray)
            ){
                NewsCard(
                    news = uiState.news[0],
                    onLikeClicked = {uiState.news[0].likes.value++}
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.Gray)
            ){
                NewsCard(
                    news = uiState.news[1],
                    onLikeClicked = {uiState.news[1].likes.value++}
                )
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.Gray)
            ){
                NewsCard(
                    news = uiState.news[2],
                    onLikeClicked = {uiState.news[2].likes.value++}
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.Gray)
            ){
                NewsCard(
                    news = uiState.news[3],
                    onLikeClicked = {uiState.news[3].likes.value++}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainLayoutPreview(){
    Lab1Theme {
        MainLayout(Modifier.fillMaxSize())
    }
}
