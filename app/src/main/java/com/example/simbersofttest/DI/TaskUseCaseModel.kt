package com.example.simbersofttest.DI

import com.example.simbersofttest.data.repository.TaskRepositoryImpl
import com.example.simbersofttest.data.source.dao.TaskDao
import com.example.simbersofttest.domain.repository.TaskRepository
import com.example.simbersofttest.domain.usecases.AddTaskUseCase
import com.example.simbersofttest.domain.usecases.DeleteTaskUseCase
import com.example.simbersofttest.domain.usecases.GetTaskDetailsUseCase
import com.example.simbersofttest.domain.usecases.GetTasksByDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TaskUseCaseModel {

    @Provides
    @Singleton
    fun provideGetTasksByDateUseCase(taskTaskRepository:TaskRepository): GetTasksByDateUseCase {
        return GetTasksByDateUseCase(taskTaskRepository)
    }

    @Provides
    @Singleton
    fun provideAddTaskUseCase(taskTaskRepository:TaskRepository): AddTaskUseCase {
        return AddTaskUseCase(taskTaskRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(taskTaskRepository:TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(taskTaskRepository)
    }


    @Provides
    @Singleton
    fun provideGetTaskDetailsUseCase(taskTaskRepository:TaskRepository): GetTaskDetailsUseCase {
        return GetTaskDetailsUseCase(taskTaskRepository)
    }

}