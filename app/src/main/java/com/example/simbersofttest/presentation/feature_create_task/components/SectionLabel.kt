package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.сonstants.Constants.Slate400

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = Slate400,
        letterSpacing = 0.5.sp
    )
}