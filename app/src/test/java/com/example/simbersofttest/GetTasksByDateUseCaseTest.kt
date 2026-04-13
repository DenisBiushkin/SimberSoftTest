package com.example.simbersofttest

import com.example.simbersofttest.domain.model.Task
import com.example.simbersofttest.domain.model.TaskCategory
import com.example.simbersofttest.domain.repository.TaskRepository
import com.example.simbersofttest.domain.usecases.GetTasksByDateUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId

class GetTasksByDateUseCaseTest {

    //Опять же проблема с зависимостью Mockito решил сделать так
    private class FakeTaskRepository : TaskRepository {
        var lastStart: Long = 0
        var lastEnd: Long = 0
        var tasksToReturn = listOf<Task>()

        override fun getTasksByDate(start: Long, end: Long): Flow<List<Task>> {
            lastStart = start
            lastEnd = end
            return flowOf(tasksToReturn)
        }

        override suspend fun addTask(task: Task) {
            TODO("Not yet implemented")
        }

        override suspend fun getTaskById(id: Int): Task? {
            TODO("Not yet implemented")
        }

        override suspend fun deleteTaskById(id: Int) {
            TODO("Not yet implemented")
        }
    }

    @Test
    fun `invoke should calculate correct day boundaries and return tasks`() {
        runBlocking {
        val fakeRepository = FakeTaskRepository()
        val useCase = GetTasksByDateUseCase(fakeRepository)

        // Выбираем конкретную дату: 2024-05-20
        val localDate = LocalDate.of(2024, 5, 20)
        val dateMillis = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

        // Ожидаемые границы
        val expectedStart = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val expectedEnd = localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault())
            .minusNanos(1).toInstant().toEpochMilli()

        // Подготовим данные
        val mockTasks = listOf(
            Task(id = 1, name = "Task 1", dateStart = expectedStart, dateFinish = expectedStart + 3600000, category = TaskCategory.WORK, description = "")
        )
        fakeRepository.tasksToReturn = mockTasks

        // Действие
        val result = useCase(dateMillis).first()

        // Проверка границ времени в репозитории
        assertEquals("Start of day mismatch", expectedStart, fakeRepository.lastStart)
        assertEquals("End of day mismatch", expectedEnd, fakeRepository.lastEnd)
        
        // Проверка возвращаемых данных
        assertEquals(1, result.size)
        assertEquals("Task 1", result[0].name)
        }
    }

    @Test
    fun `invoke should handle different time zones via systemDefault`() = runBlocking {
        // Проверка на то, что расчет корректно работает для миллисекунд, 
        // пришедших из середины дня
        val fakeRepository = FakeTaskRepository()
        val useCase = GetTasksByDateUseCase(fakeRepository)

        // 2024-05-20 в 15:30:00
        val midDayMillis = LocalDate.of(2024, 5, 20)
            .atTime(15, 30)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val expectedStart = LocalDate.of(2024, 5, 20)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        useCase(midDayMillis).first()

        // Даже если передали время в середине дня, поиск должен идти с 00:00:00
        assertEquals(expectedStart, fakeRepository.lastStart)
    }
}