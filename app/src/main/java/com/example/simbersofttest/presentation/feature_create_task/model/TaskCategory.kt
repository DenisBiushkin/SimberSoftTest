package com.example.simbersofttest.presentation.feature_create_task.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.simbersofttest.сonstants.Constants.Blue500

enum class TaskCategory(val title: String, val color: Color, val icon: ImageVector) {
    WORK("Работа", Blue500, Icons.Default.Build),
    PERSONAL("Личное", Color(0xFFA855F7), Icons.Default.AccountCircle),
    STUDY("Учеба", Color(0xFFF59E0B), Icons.Default.Star),
    HEALTH("Здоровье", Color(0xFF10B981), Icons.Default.Favorite)
}