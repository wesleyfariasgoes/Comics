package com.example.comics.presentation.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.comics.presentation.components.HeaderSection

@Composable
fun HomeScreen() {
    Box(Modifier.fillMaxSize()) {
        Column {
            HeaderSection(onBellClick = {})
            MovieScreen()
        }
    }
}