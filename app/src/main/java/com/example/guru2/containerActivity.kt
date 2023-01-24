package com.example.guru2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class containerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val transaction = supportFragmentManager.beginTransaction()
        .add(R.id.fragment_container, RecordMain())
        transaction.commit()
    }
}