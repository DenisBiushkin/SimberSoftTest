package com.example.simbersofttest.presentation.feature_create_task.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simbersofttest.сonstants.Constants.Blue600
import com.example.simbersofttest.сonstants.Constants.Slate100
import com.example.simbersofttest.сonstants.Constants.Slate400
import com.example.simbersofttest.сonstants.Constants.Slate50
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WheelTimePicker(hours: List<Int>, selectedHour: Int, onHourChanged: (Int) -> Unit) {
    val itemHeight = 44.dp
    val visibleItems = 3
    val itemsWithPaddings = listOf(-1) + hours + listOf(-1)
    
    val initialIndex = hours.indexOf(selectedHour).coerceAtLeast(0)
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                if (index in hours.indices) {
                    onHourChanged(hours[index])
                }
            }
    }

    Box(
        modifier = Modifier.run {
            fillMaxWidth()
                .height(itemHeight * visibleItems)
                .clip(RoundedCornerShape(24.dp))
                .background(Slate50)
                .border(1.dp, Slate100, RoundedCornerShape(24.dp))
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .align(Alignment.Center)
                .padding(horizontal = 12.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(1.dp, Slate100, RoundedCornerShape(12.dp))
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(itemsWithPaddings.size) { index ->
                val hour = itemsWithPaddings[index]
                Box(
                    modifier = Modifier.height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    if (hour != -1) {
                        val isSelected = hour == selectedHour
                        Text(
                            text = "${hour.toString().padStart(2, '0')}:00",
                            fontSize = if (isSelected) 18.sp else 15.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                            color = if (isSelected) Blue600 else Slate400
                        )
                    }
                }
            }
        }

        // Overlay gradients
        Column(Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth().weight(1f).background(Brush.verticalGradient(listOf(Slate50.copy(alpha = 0.8f), Color.Transparent))))
            Spacer(modifier = Modifier.height(itemHeight))
            Box(modifier = Modifier.fillMaxWidth().weight(1f).background(Brush.verticalGradient(listOf(Color.Transparent, Slate50.copy(alpha = 0.8f)))))
        }
    }
}