package com.example.guru2

import android.graphics.drawable.Drawable
import androidx.room.Entity

@Entity(tableName = "meal")
data class MealRecModel (
    val mealImg: Drawable?,
    var timeSlot: String = "아침",
    val eatTime: String,
    var mealName: String
)