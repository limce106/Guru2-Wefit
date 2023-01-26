package com.example.guru2.Records

import androidx.room.Entity

@Entity(tableName = "meal")
data class MealRecModel(
    //val mealImg: Drawable?,
    var eatDate: String = "날짜",
    var timeSlot: String = "저녁",
    var eatTime: String = "오후7시40분",
    var mealName: String = "프로틴"
)