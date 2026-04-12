package com.example.simbersofttest.presentation.feature_detail_task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.presentation.feature_detail_task.model.TaskDetailVMState

@Composable
fun TaskDetailsContent(state: TaskDetailVMState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = state.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF0F172A),
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoBadge(
                icon = Icons.Default.DateRange,
                text = state.formattedDate,
                backgroundColor = Color(0xFFEFF6FF),
                contentColor = Color(0xFF1E40AF)
            )
            InfoBadge(
                icon = Icons.Default.Info,
                text = state.formattedTimeRange,
                backgroundColor = Color(0xFFFFF7ED),
                contentColor = Color(0xFF9A3412)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        DescriptionBlock(description = state.description)
    }
}
