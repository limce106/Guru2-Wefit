package com.example.guru2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Choice : AppCompatActivity() {

    lateinit var btn_member:Button
    lateinit var btn_trainer:Button
    lateinit var btn_goLogin:Button



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        btn_member=findViewById(R.id.btn_member)
        btn_trainer=findViewById(R.id.btn_trainer)
        btn_goLogin=findViewById(R.id.btn_goLogin)



        btn_member.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_trainer.setOnClickListener {
            val intent = Intent(this,trainerRegister::class.java)
            startActivity(intent)
        }

        btn_goLogin.setOnClickListener {

            val intent = Intent(this,activity_login::class.java)
            startActivity(intent)
        }





    }
}