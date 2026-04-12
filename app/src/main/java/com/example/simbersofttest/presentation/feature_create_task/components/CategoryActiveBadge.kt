package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.сonstants.Constants.Blue50
import com.example.simbersofttest.сonstants.Constants.Blue600

@Composable
fun CategoryActiveBadge(text: String) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Blue50)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Blue600)
    }
}