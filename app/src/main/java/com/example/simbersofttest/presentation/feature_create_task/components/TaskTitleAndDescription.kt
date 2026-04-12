package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.сonstants.Constants.Slate300
import com.example.simbersofttest.сonstants.Constants.Slate500
import com.example.simbersofttest.сonstants.Constants.Slate900
import java.time.format.TextStyle

@Composable
fun TaskTitleAndDescription(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        BasicTextField(
            value = title,
            onValueChange = onTitleChange,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900
            ),
            decorationBox = { innerTextField ->
                if (title.isEmpty()) Text("Заголовок", color = Slate300, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                innerTextField()
            },
            modifier = Modifier.fillMaxWidth()
        )
        BasicTextField(
            value = description,
            onValueChange = onDescChange,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Slate500
            ),
            decorationBox = { innerTextField ->
                if (description.isEmpty()) Text("Описание...", color = Slate300, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                innerTextField()
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

