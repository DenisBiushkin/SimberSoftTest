package com.example.simbersofttest.data.repository

import com.example.simbersofttest.data.source.dao.TaskDao
import com.example.simbersofttest.data.source.entity.TaskEntity
import com.example.simbersofttest.domain.model.Task
import com.example.simbersofttest.domain.repository.TaskRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override  fun getTasksByDate(dateStartOfDay: Long, dateEndOfDay: Long): Flow<List<Task>> {
        return taskDao.getTasksForRange(dateStartOfDay, dateEndOfDay)
            .map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addTask(task: Task) {
        taskDao.insertTask(task.toEntity())
    }

    override suspend fun getTaskById(id: Int): Task? {
        return taskDao.getTaskById(id)?.toDomain()
    }

    override suspend fun deleteTaskById(id: Int) {
            TODO("Not yet implemented")
    }
}
fun TaskEntity.toDomain() =
    Task(id, dateStart, dateFinish, name, description, TaskCategoryMapper.fromInt(category))
fun Task.toEntity() =
    TaskEntity(id,  name, description,dateStart, dateFinish,TaskCategoryMapper.toInt(category))
