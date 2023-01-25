package com.example.guru2

import androidx.room.Entity

@Entity(tableName = "meal")
data class MealRecModel (
    val mealImg: String = "",
    var eatDate: String = "",
    var timeSlot: String = "",
    var eatTime: String = "",
    var mealName: String = ""
)