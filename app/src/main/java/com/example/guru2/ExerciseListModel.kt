package com.example.guru2
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_exerciseName")
data class ExerciseListModel(
    @PrimaryKey(autoGenerate = true) val exerciseName: String = ""
)