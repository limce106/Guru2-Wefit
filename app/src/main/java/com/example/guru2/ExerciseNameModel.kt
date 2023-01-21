package com.example.guru2

import androidx.room.Entity

@Entity(tableName = "exercisename")
data class ExerciseNameModel(
    val exerciseName: String = ""
)