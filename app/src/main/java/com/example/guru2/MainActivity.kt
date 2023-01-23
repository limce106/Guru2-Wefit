package com.example.guru2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_input_meal.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Button1 = findViewById<Button>(R.id.button)
        val loginTest=findViewById<Button>(R.id.loginTest)

        fun moveToAnotherPage(){
            val intent = Intent(this,ExerciseRec_main::class.java)
            startActivity(intent)
        }

        Button1.setOnClickListener{
            moveToAnotherPage()
        }

        loginTest.setOnClickListener {
            val intent = Intent(this,activity_login::class.java)
            startActivity(intent)
        }
    }

    companion object{
        private var instance:MainActivity? = null
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    override fun  onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val REQUEST_CODE = 1

        if(resultCode != Activity.RESULT_OK) {
            return
        }
        when(requestCode){
            REQUEST_CODE->{
                data?:return
                val uri=data.data as Uri
                mealImg.setImageURI(uri);
            }

            else->{
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun changeFragment(index: Int) {
        when (index) {
            1 -> {
                val inputExerciseFragment = InputExerciseFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.exerciseRecordContainer, inputExerciseFragment)
                    .commit()
            }

            2 -> {
                val inputMealFragment = InputMealFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.exerciseRecordContainer, inputMealFragment)
                    .commit()
            }

            3 -> {
                val exerciseRecordFragment = ExerciseRecordFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.exerciseRecordContainer, exerciseRecordFragment)
                    .commit()
            }

            4 -> {
                val mealRecordFragment = MealRecordFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.exerciseRecordContainer, mealRecordFragment)
                    .commit()
            }
        }
    }
}