package com.example.guru2

import androidx.room.Entity

@Entity(tableName = "exercise")
data class ExerciseRecModel(
    val exerName2: String = "",
    val count: String = "",
    val set: String = ""
)