package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.сonstants.Constants.Blue600
import com.example.simbersofttest.сonstants.Constants.Slate400
import com.example.simbersofttest.сonstants.Constants.Slate50
import com.example.simbersofttest.сonstants.Constants.Slate900

@Composable
fun AddTaskTopBar(onCancel: () -> Unit, onDone: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onCancel,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Slate50)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Отмена", tint = Slate400)
        }
        Text("Новое событие", fontWeight = FontWeight.Bold, color = Slate900, fontSize = 20.sp)
        Text(
            "Сохранить", Modifier.clickable { onDone() }, Blue600, fontWeight = FontWeight.ExtraBold
        )
    }
}