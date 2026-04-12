package com.example.simbersofttest.presentation.feature_detail_task.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.simbersofttest.presentation.feature_detail_task.components.TaskDetailsContent
import com.example.simbersofttest.presentation.feature_detail_task.components.TaskDetailsTopBar
import com.example.simbersofttest.presentation.feature_detail_task.model.TaskDetailsEffect
import com.example.simbersofttest.presentation.feature_detail_task.model.TaskDetailsEvent
import com.example.simbersofttest.presentation.feature_detail_task.viewmodel.DetailTaskViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun TaskDetailScreen(
    taskId:Int,
    viewModel: DetailTaskViewModel,
    onNavigateBack: () -> Unit,
) {
    val state = viewModel.state.collectAsState()
    val snackbarHostState: SnackbarHostState = SnackbarHostState()
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is TaskDetailsEffect.NavigateBack -> onNavigateBack()
                is TaskDetailsEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TaskDetailsTopBar(
                onBack = { viewModel.onEvent(TaskDetailsEvent.OnBackClicked) },
                onEdit = { viewModel.onEvent(TaskDetailsEvent.OnEditClicked) },
                onDelete = { viewModel.onEvent(TaskDetailsEvent.OnDeleteClicked) }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF2563EB)
                )
            } else {
                TaskDetailsContent(state = state.value)
            }
        }
    }
}
