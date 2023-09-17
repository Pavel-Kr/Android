package com.example.lab1

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SingleNews(
    val newsText: String = "",
    var likes: MutableState<Int> = mutableStateOf(0)
)
