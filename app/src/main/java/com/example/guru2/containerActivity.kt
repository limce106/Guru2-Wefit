package com.example.guru2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class containerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val transaction = supportFragmentManager.beginTransaction()
        .add(R.id.fragment_container, InputMealFragment())
        transaction.commit()
    }

    companion object{
        private var instance:containerActivity? = null
        fun getInstance(): containerActivity? {
            return instance
        }
    }

    //프래그먼트 변경 함수
    fun setFrag(fragNum: Int){
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0->{
                ft.replace(R.id.fragment_container,InputExerciseFragment()).commit()
            }
            1->{
                ft.replace(R.id.main_frame,InputMealFragment()).commit()
            }
            2->{
                ft.replace(R.id.main_frame,ExerciseRecordFragment()).commit()
            }
            3->{
                ft.replace(R.id.main_frame,MealRecordFragment()).commit()
            }

        }

    }
}