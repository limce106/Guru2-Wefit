package com.example.guru2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class firstPage : AppCompatActivity() {
    lateinit var btn_start:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)

        btn_start=findViewById(R.id.btn_start)

        btn_start.setOnClickListener {


        }

        moveMain(1);

    }


    fun moveMain(sec:Int){
        try {
            Thread.sleep(2000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val intent = Intent(this,Choice::class.java)
        startActivity(intent)
        finish()

    }
}