package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.presentation.feature_create_task.model.TaskCategory
import com.example.simbersofttest.сonstants.Constants.Blue50
import com.example.simbersofttest.сonstants.Constants.Blue600

@Composable
fun CategoryIconItem(category: TaskCategory, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(category.color)
            .clickable { onClick() }
            .then(
                if (isSelected) Modifier.border(4.dp, Color.White, RoundedCornerShape(16.dp))
                else Modifier
            )
            .padding(if (isSelected) 4.dp else 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(category.icon, contentDescription = category.title, tint = Color.White, modifier = Modifier.size(20.dp))
    }
}