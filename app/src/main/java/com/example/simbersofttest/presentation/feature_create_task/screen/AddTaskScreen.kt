package com.example.simbersofttest.presentation.feature_create_task.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simbersofttest.presentation.feature_create_task.components.AddTaskTopBar
import com.example.simbersofttest.presentation.feature_create_task.components.CategorySelector
import com.example.simbersofttest.presentation.feature_create_task.components.DatePickerField
import com.example.simbersofttest.presentation.feature_create_task.components.SaveButton
import com.example.simbersofttest.presentation.feature_create_task.components.TaskDatePickerDialog
import com.example.simbersofttest.presentation.feature_create_task.components.TaskTitleAndDescription
import com.example.simbersofttest.presentation.feature_create_task.components.TimeIntervalPreview
import com.example.simbersofttest.presentation.feature_create_task.components.TimeWheelSelector
import com.example.simbersofttest.presentation.feature_create_task.model.AddTaskEvent
import com.example.simbersofttest.presentation.feature_create_task.model.AddTaskState
import com.example.simbersofttest.presentation.feature_create_task.model.AddTaskUiEffect
import com.example.simbersofttest.presentation.feature_create_task.viewmodel.AddTaskViewModel


@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel= hiltViewModel(),
    onNavigateBack:()->Unit
) {
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is AddTaskUiEffect.NavigateBack -> onNavigateBack()
                is AddTaskUiEffect.ShowError -> {
                    snackbarHostState.showSnackbar(message = effect.message)
                }
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            AddTaskTopBar(
                onCancel = { viewModel.onEvent(AddTaskEvent.OnCancelClick) },
                onDone = { viewModel.onEvent(AddTaskEvent.OnSaveClick) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            TaskTitleAndDescription(
                title = state.value.title,
                description = state.value.description,
                onTitleChange = { viewModel.onEvent(AddTaskEvent.OnTitleChanged(it)) },
                onDescChange = { viewModel.onEvent(AddTaskEvent.OnDescChanged(it)) }
            )

            DatePickerField(
                dateMillis = state.value.selectedDateMillis,
                onClick = { viewModel.onEvent(AddTaskEvent.ToggleDatePicker) }
            )

            TimeIntervalPreview(startHour = state.value.startHour)

            TimeWheelSelector(
                selectedHour = state.value.startHour,
                onHourSelected = { viewModel.onEvent(AddTaskEvent.OnHourSelected(it)) }
            )

            CategorySelector(
                selectedCategory = state.value.category,
                onCategorySelected = {
                    viewModel.onEvent(AddTaskEvent.OnCategorySelected(it))
                }
            )

            SaveButton(onSave = { viewModel.onEvent(AddTaskEvent.OnSaveClick) })
        }

        if (state.value.isDatePickerOpen) {
            TaskDatePickerDialog(
                initialDateMillis = state.value.selectedDateMillis,
                onDateSelected = { viewModel.onEvent(AddTaskEvent.OnDateSelected(it)) },
                onDismiss = { viewModel.onEvent(AddTaskEvent.ToggleDatePicker) }
            )
        }
    }
}


