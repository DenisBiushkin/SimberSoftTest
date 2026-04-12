package com.example.simbersofttest.presentation.feature_create_task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simbersofttest.domain.usecases.AddTaskUseCase
import com.example.simbersofttest.presentation.feature_create_task.model.AddTaskUiEffect
import com.example.simbersofttest.presentation.feature_create_task.model.AddTaskEvent
import com.example.simbersofttest.presentation.feature_create_task.model.AddTaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AddTaskState())
    val state: StateFlow<AddTaskState> = _state.asStateFlow()

    // Одноразовые события
    private val _uiEffect = MutableSharedFlow<AddTaskUiEffect>()
    val uiEffect: SharedFlow<AddTaskUiEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: AddTaskEvent) {
        when (event) {
            is AddTaskEvent.OnTitleChanged -> {
                _state.update { it.copy(title = event.title) }
            }

            is AddTaskEvent.OnDescChanged -> {
                _state.update { it.copy(description = event.desc) }
            }

            is AddTaskEvent.OnHourSelected -> {
                _state.update { it.copy(startHour = event.hour) }
            }

            is AddTaskEvent.OnCategorySelected -> {
                _state.update { it.copy(category = event.category) }
            }

            is AddTaskEvent.ToggleDatePicker -> {
                _state.update { it.copy(isDatePickerOpen = !it.isDatePickerOpen) }
            }

            is AddTaskEvent.OnDateSelected -> {
                event.millis?.let { selectedMillis ->
                    _state.update {
                        it.copy(
                            selectedDateMillis = selectedMillis,
                            isDatePickerOpen = false
                        )
                    }
                } ?: _state.update { it.copy(isDatePickerOpen = false) }
            }

            AddTaskEvent.OnSaveClick -> {
                saveTask()
            }

            AddTaskEvent.OnCancelClick -> {
                sendEffect(AddTaskUiEffect.NavigateBack)
            }
        }
    }


    private fun saveTask() {
        val currentState = _state.value
        if (currentState.title.isBlank()) {
            sendEffect(AddTaskUiEffect.ShowError("Название задачи не может быть пустым"))
            return
        }

        viewModelScope.launch {
            try {
                addTaskUseCase.execute(
                    title = currentState.title,
                    description = currentState.description,
                    dateMillis = currentState.selectedDateMillis,
                    hour = currentState.startHour,
                    category = currentState.category.name
                )

                sendEffect(AddTaskUiEffect.NavigateBack)
            } catch (e: Exception) {
                sendEffect(AddTaskUiEffect.ShowError("Не удалось сохранить задачу: ${e.message}"))
            }
        }
    }


    private fun sendEffect(effect: AddTaskUiEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}