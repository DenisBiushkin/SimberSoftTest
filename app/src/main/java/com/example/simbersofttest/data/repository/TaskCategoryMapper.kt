package com.example.simbersofttest.data.repository

import com.example.simbersofttest.domain.model.TaskCategory

object TaskCategoryMapper {
    fun toInt(category: TaskCategory): Int = category.ordinal

    fun fromInt(value: Int): TaskCategory {
        return TaskCategory.values().getOrElse(value) { TaskCategory.WORK }
    }
}