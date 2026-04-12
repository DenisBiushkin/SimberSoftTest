package com.example.simbersofttest.presentation.feature_main_calendar_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.domain.model.Task
import java.util.Calendar

@Composable
fun TimeRow(
    hour: Int,
    //TODO переделать под TaskUi
    tasks: List<Task>,
    onTaskClick: (Int) -> Unit
) {
    //TODO переделать
    // Ищем задачи, которые начинаются в этот час (упрощенная логика для ТЗ)
    val taskAtThisHour = tasks.find { 
        val calendar = Calendar.getInstance().apply { timeInMillis = it.dateStart }
        calendar.get(Calendar.HOUR_OF_DAY) == hour 
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Позволяет линии растягиваться по высоте контента
    ) {
        // Колонка времени
        Text(
            text = String.format("%02d:00", hour),
            modifier = Modifier.width(45.dp).padding(top = 4.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )

        // Разделительная линия с точкой
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color(0xFFE2E8F0))
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(top = 4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFCBD5E1))
            )
        }

        // Карточка задачи
        Box(modifier = Modifier.weight(1f).padding(bottom = 16.dp)) {
            if (taskAtThisHour != null) {
                TaskCard(task = taskAtThisHour, onClick = { onTaskClick(taskAtThisHour.id) })
            } else {
                Spacer(modifier = Modifier.height(40.dp).fillMaxWidth())
            }
        }
    }
}