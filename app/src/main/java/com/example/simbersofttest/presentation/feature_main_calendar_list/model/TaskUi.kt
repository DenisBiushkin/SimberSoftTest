package com.example.simbersofttest.presentation.feature_main_calendar_list.model


import androidx.compose.ui.graphics.Color
import com.example.simbersofttest.presentation.feature_create_task.model.TaskCategoryUi

data class TaskUi(
    val id: Int,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String,
    val category: TaskCategoryUi,//небольшое нарушение слоёв
    val color: Color = category.color // Цвет берется напрямую из категории
)