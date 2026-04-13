package com.example.simbersofttest


import com.example.simbersofttest.domain.model.Task
import com.example.simbersofttest.domain.model.TaskCategory
import com.example.simbersofttest.domain.repository.TaskRepository
import com.example.simbersofttest.domain.usecases.AddTaskUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertNotNull
import java.util.Calendar

class AddTaskUseCaseTest {

    //Можно через Mockito но с зависимостью что то не то
    private class FakeTaskRepository : TaskRepository {
        var capturedTask: Task? = null
        var callCount = 0
        override fun getTasksByDate(
            dateStartOfDay: Long,
            dateEndOfDay: Long
        ): Flow<List<Task>> {
            TODO("Not yet implemented")
        }

        override suspend fun addTask(task: Task) {
            capturedTask = task
            callCount++
        }

        override suspend fun getTaskById(id: Int): Task? {
            TODO("Not yet implemented")
        }

        override suspend fun deleteTaskById(id: Int) {
            TODO("Not yet implemented")
        }

    }

    @Test
    fun `execute should prepare task with correct timestamps`() {
        runBlocking {
            val fakeRepository = FakeTaskRepository()
            val addTaskUseCase = AddTaskUseCase(fakeRepository)

            val title = "Test Task"
            val description = "Test Description"
            val category = TaskCategory.WORK

            val calendar = Calendar.getInstance().apply {
                set(2024, Calendar.SEPTEMBER, 1, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val dateMillis = calendar.timeInMillis
            val startHour = 10

            // Выполнение
            addTaskUseCase.execute(title, description, dateMillis, startHour, category)

            // Проверки
            assertEquals(1, fakeRepository.callCount)
            val task = fakeRepository.capturedTask
            assertNotNull(task)

            task?.let {
                assertEquals(title, it.name)
                assertEquals(10, Calendar.getInstance().apply { timeInMillis = it.dateStart }.get(Calendar.HOUR_OF_DAY))
                assertEquals(11, Calendar.getInstance().apply { timeInMillis = it.dateFinish }.get(Calendar.HOUR_OF_DAY))
            }
        }
    }
}