package com.example.simbersofttest.presentation.feature_main_calendar_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simbersofttest.domain.model.Task

@Composable
fun TimelineSection(
    tasks: List<Task>,
    onTaskClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(24) { hour ->
            TimeRow(hour = hour, tasks = tasks, onTaskClick = onTaskClick)
        }
    }
}
