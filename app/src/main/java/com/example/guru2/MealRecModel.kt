package com.example.guru2

import androidx.room.Entity

@Entity(tableName = "meal")
data class MealRecModel (
    //val mealImg: Drawable?,
    val timeSlot: String = "아침",
    //val eatTime: String,
    val mealName: String
)