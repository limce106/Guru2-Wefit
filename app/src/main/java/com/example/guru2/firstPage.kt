package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button

class firstPage : AppCompatActivity() {
    lateinit var btn_start:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)

        btn_start=findViewById(R.id.btn_start)

        btn_start.setOnClickListener {
                val intent = Intent(this,Choice::class.java)
                startActivity(intent)

        }


    }
}