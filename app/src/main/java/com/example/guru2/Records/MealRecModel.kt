package com.example.guru2.Records

import androidx.room.Entity

@Entity(tableName = "meal")
data class MealRecModel (
    //val mealImg: Drawable?,
    var eatDate: String = "",
    var timeSlot: String = "",
    var eatTime: String = "",
    var mealName: String = ""
)