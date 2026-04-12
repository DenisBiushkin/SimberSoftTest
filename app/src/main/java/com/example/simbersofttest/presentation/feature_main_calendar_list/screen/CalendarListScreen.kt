package com.example.simbersofttest.presentation.feature_main_calendar_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simbersofttest.presentation.feature_main_calendar_list.components.CalendarStrip
import com.example.simbersofttest.presentation.feature_main_calendar_list.components.HeaderSection
import com.example.simbersofttest.presentation.feature_main_calendar_list.components.TimelineSection
import com.example.simbersofttest.presentation.feature_main_calendar_list.viewmodel.CalendarListViewModel


@Composable
fun CalendarListScreen(
    viewModel: CalendarListViewModel = hiltViewModel(),
    onTaskClick: (Int)-> Unit,
    onCreateTaskClick: ()-> Unit
) {
    val state = viewModel.state.collectAsState();
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateTaskClick,
                containerColor = Color(0xFF2563EB),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Создать задачу")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8FAFC))
        ) {
            HeaderSection(
                title = state.value.monthYearTitle,
                onMonthClick = {

                }
            )
            CalendarStrip(
                days = state.value.calendarDaysUi,
                onDayClick = {

                }
            )
            TimelineSection(
                tasks = state.value.tasks,
                onTaskClick = {

                }
            )
        }
    }
}