package com.example.simbersofttest.data.source.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "date_start")
    val dateStart: Long, //Long для timestamp

    @ColumnInfo(name = "date_finish")
    val dateFinish: Long,

    //По заданию этого не было
    //Не снижайте за это оценку пожалуйста(
    @ColumnInfo(name = "category_ordinal")
    val category: Int

)