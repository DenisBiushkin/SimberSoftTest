package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.сonstants.Constants.Blue100
import com.example.simbersofttest.сonstants.Constants.Blue600
import com.example.simbersofttest.сonstants.Constants.Slate100
import com.example.simbersofttest.сonstants.Constants.Slate50
import com.example.simbersofttest.сonstants.Constants.Slate800


@Composable
fun TimeIntervalPreview(startHour: Int) {
    val nextHour = if (startHour + 1 > 23) 0 else startHour + 1
    val timeRangeText = "${startHour.toString().padStart(2, '0')}:00 - ${nextHour.toString().padStart(2, '0')}:00"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Slate50)
            .border(1.dp, Slate100, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Blue100),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = Blue600, modifier = Modifier.size(20.dp))
            }
            Column {
                SectionLabel("ИНТЕРВАЛ")
                Text(text = timeRangeText, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Slate800)
            }
        }
        Badge(text = "1 ЧАС")
    }
}