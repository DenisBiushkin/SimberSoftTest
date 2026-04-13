package com.example.simbersofttest.presentation.feature_main_calendar_list.components



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.simbersofttest.presentation.feature_main_calendar_list.model.CalendarDayUi

@Composable
fun CalendarStrip(
    days: List<CalendarDayUi>,
    showNextButton: Boolean = false,
    onDayClick: (Int) -> Unit,
    onNextPageClick: ()->Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val horizontalPadding = 20.dp
    val spacing = 12.dp
    val itemsInRow = 7

    // Вычисляем ширину для каждого элемента недели чтобы помещалось ровно 7 штук
    val itemSize = (screenWidth - (horizontalPadding * 2) - (spacing * (itemsInRow - 1))) / itemsInRow

    val listState = rememberLazyListState()

    LaunchedEffect(days) {
        val selectedIndex = days.indexOfFirst { it.isSelected }
        // Если выбранный элемент найден и общее кол-во элементов больше 3
        if (selectedIndex != -1 && days.size > 3) {
            val screenCenter = screenWidth / 2
            val halfItem = itemSize / 2
            val scrollOffset = (screenCenter - halfItem - horizontalPadding).value.toInt()
            listState.animateScrollToItem(
                index = selectedIndex,
                scrollOffset = -scrollOffset // Отрицательное значение смещает элемент вправо
            )
        }
    }
    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        // Список дней
        items(days) { dayState ->
            DayItem(
                state = dayState,
                itemWidth = itemSize,
                onClick = { onDayClick(dayState.dayNumber) }
            )
        }

        //TODO не будет реализована в этом MVP
        // Кнопка перехода, доступная по флагу
        if (showNextButton) {
            item {
                NextPageButton(
                    size = itemSize,
                    onClick = onNextPageClick
                )
            }
        }
    }
}

