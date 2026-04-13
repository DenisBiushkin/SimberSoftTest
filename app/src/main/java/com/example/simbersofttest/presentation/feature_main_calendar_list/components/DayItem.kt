package com.example.simbersofttest.presentation.feature_main_calendar_list.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarDayUi

@Composable
fun DayItem(
    state: CalendarDayUi,
    itemWidth: Dp,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(itemWidth)
            .height(75.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (state.isSelected) Color(0xFF2563EB) else Color(0xFFF1F5F9))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = state.dayOfWeek,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = if (state.isSelected) Color.White else Color.Gray
        )
        Text(
            text = state.dayNumber.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (state.isSelected) Color.White else Color(0xFF0F172A)
        )
    }
}