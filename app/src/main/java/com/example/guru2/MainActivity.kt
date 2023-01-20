package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Button1 = findViewById<Button>(R.id.button)

        fun moveToAnotherPage(){
            val intent = Intent(this,ExerciseRec_main::class.java)
            startActivity(intent)
        }


        Button1.setOnClickListener{

            moveToAnotherPage()


        }
    }

}