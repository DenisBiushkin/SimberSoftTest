package com.example.simbersofttest.presentation.navigation

sealed class Screens(val route: String) {
    // Главный экран со списком
    object Main : Screens("main_screen")
    
    // Экран создания задачи
    object CreateTask : Screens("create_task_screen")
    
    // Экран деталей (принимает taskId как аргумент)
    object TaskDetail : Screens("task_detail_screen/{taskId}") {
        fun createRoute(taskId: Int) = "task_detail_screen/$taskId"
    }
}