package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TimeWheelSelector(selectedHour: Int, onHourSelected: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionLabel("ВЫБРАТЬ ВРЕМЯ НАЧАЛА")
        WheelTimePicker(
            hours = (0..23).toList(),
            selectedHour = selectedHour,
            onHourChanged = onHourSelected
        )
    }
}