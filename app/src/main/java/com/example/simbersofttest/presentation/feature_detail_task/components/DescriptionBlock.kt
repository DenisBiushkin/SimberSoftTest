package com.example.simbersofttest.presentation.feature_detail_task.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionBlock(description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Text(
            text = "ОПИСАНИЕ",
            fontWeight = FontWeight.Black,
            color = Color(0xFF94A3B8),
            fontSize = 11.sp,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color(0xFF334155),
            lineHeight = 24.sp
        )
    }
}