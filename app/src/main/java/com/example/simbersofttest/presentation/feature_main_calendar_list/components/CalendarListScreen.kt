package com.example.simbersofttest.presentation.feature_main_calendar_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun CalendarListScreen(
    onTaskClick: (Int)-> Unit,
    onCreateTaskClick: ()-> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Task List Screen")
        Button(onClick = {
            onTaskClick(2)
        }){
            Text("Go to Detail Screen")
        }
    }
}