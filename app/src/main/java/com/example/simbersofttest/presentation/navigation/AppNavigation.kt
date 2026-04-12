package com.example.simbersofttest.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.simbersofttest.presentation.feature_create_task.screen.AddTaskScreen
import com.example.simbersofttest.presentation.feature_detail_task.TaskDetailScreen
import com.example.simbersofttest.presentation.feature_main_calendar_list.screen.CalendarListScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = Screens.Main.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Главный экран
        composable(Screens.Main.route) {
            CalendarListScreen(
                onTaskClick = { taskId ->
                    navController.navigate(Screens.TaskDetail.createRoute(taskId))
                },
                onCreateTaskClick = {
                    navController.navigate(Screens.CreateTask.route)
                }
            )
        }

        // Экран деталей
        composable(
            route = Screens.TaskDetail.route,
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            TaskDetailScreen(taskId)
        }

        // Экран создания
        composable(Screens.CreateTask.route) {
            AddTaskScreen(onNavigateBack = {
                navController.navigate(Screens.Main.route)
            })
        }
    }
}