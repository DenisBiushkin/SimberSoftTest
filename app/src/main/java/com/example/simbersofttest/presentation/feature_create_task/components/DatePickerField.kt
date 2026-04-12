package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.сonstants.Constants.Slate100
import com.example.simbersofttest.сonstants.Constants.Slate400
import com.example.simbersofttest.сonstants.Constants.Slate500
import java.time.Instant
import java.time.Instant.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun DatePickerField(dateMillis: Long, onClick: () -> Unit) {
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru")) }
    val dateText = remember(dateMillis) {

        ofEpochMilli(dateMillis).atZone(ZoneId.systemDefault()).toLocalDate().format(dateFormatter)
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionLabel("ДАТА ВЫПОЛНЕНИЯ")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, Slate100, RoundedCornerShape(20.dp))
                .clickable { onClick() }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(dateText, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Slate500)
            Icon(Icons.Default.DateRange, contentDescription = null, tint = Slate400, modifier = Modifier.size(20.dp))
        }
    }
}