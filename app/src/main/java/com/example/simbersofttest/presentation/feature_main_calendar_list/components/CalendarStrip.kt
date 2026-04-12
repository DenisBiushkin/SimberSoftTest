package com.example.simbersofttest.presentation.feature_main_calendar_list.components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarDayUi

@Composable
fun CalendarStrip(
    days: List<CalendarDayUi>,
    onDayClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(days) { dayState ->
            DayItem(
                state = dayState,
                onClick = { onDayClick(dayState.dayNumber) }
            )
        }
    }
}
