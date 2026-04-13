package com.example.simbersofttest.presentation.feature_main_calendar_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun NextPageButton(
    size: Dp,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(width = size / 1.5f, height = 75.dp) // Чуть уже основного элемента
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        color = Color(0xFFF1F5F9)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next",
                tint = Color(0xFF2563EB)
            )
        }
    }
}