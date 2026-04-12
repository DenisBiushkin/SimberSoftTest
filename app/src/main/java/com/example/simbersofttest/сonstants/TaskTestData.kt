package com.example.simbersofttest.сonstants

import com.example.simbersofttest.domain.model.Task
import java.util.Calendar

object TaskTestData {

    // Вспомогательная функция для генерации timestamp для конкретного часа сегодня
    private fun getTimestampForHour(hour: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    val list: List<Task> = listOf(
        Task(
            id = 1,
            dateStart = getTimestampForHour(9),
            dateFinish = getTimestampForHour(10),
            name = "Планерка команды",
            description = "Обсуждение спринта и текущих задач на неделю. Подготовить отчет по багам."
        ),
        Task(
            id = 2,
            dateStart = getTimestampForHour(12),
            dateFinish = getTimestampForHour(13),
            name = "Обед",
            description = "Перерыв на обед. Сходить в кафе через дорогу."
        ),
        Task(
            id = 3,
            dateStart = getTimestampForHour(14),
            dateFinish = getTimestampForHour(15),
            name = "Собеседование в SimbirSoft",
            description = "Подготовить рассказ о себе, повторить архитектурные паттерны (MVVM, Clean Architecture), освежить знания по Kotlin Coroutines и Room."
        ),
        Task(
            id = 4,
            dateStart = getTimestampForHour(18),
            dateFinish = getTimestampForHour(19),
            name = "Тренировка",
            description = "Силовая тренировка в зале. День ног и спины."
        ),
        Task(
            id = 5,
            dateStart = getTimestampForHour(20),
            dateFinish = getTimestampForHour(21),
            name = "Чтение книги",
            description = "Прочитать 2 главы 'Чистой архитектуры' Роберта Мартина."
        )
    )
}