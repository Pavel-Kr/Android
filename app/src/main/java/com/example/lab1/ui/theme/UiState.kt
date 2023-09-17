package com.example.lab1.ui.theme

import com.example.lab1.SingleNews

data class UiState(
    val news: List<SingleNews> = mutableListOf(),
    val index: Int = 0
)
