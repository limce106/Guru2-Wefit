package com.example.guru2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlin.math.log

class activity_login : AppCompatActivity() {

    lateinit var btn_register: Button
    lateinit var btn_login:Button
    lateinit var edit_id:EditText
    lateinit var edit_pw:EditText

    private lateinit var auth:FirebaseAuth

    private val TAG:String=activity_login::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth=FirebaseAuth.getInstance()

        btn_login=findViewById(R.id.btn_login)
        btn_register=findViewById(R.id.btn_register)
        edit_id=findViewById(R.id.edit_id)
        edit_pw=findViewById(R.id.edit_pw)

        btn_login.setOnClickListener {
             val loginID=edtID.text.toString()
             val loginPW=edtPW.text.toString()

            if(edit_id.text.isEmpty()||edit_pw.text.isEmpty()){
                Toast.makeText(this, "모든 항목을 채워주세요", Toast.LENGTH_SHORT)
                    .show()
            }
            auth.createUserWithEmailAndPassword(loginID,loginPW)
                .addOnCompleteListener(this){ task->
                    if(task.isSuccessful){

                        Log.d(TAG, "login 성공")
                    }else{
                        Log.d(TAG, "login 실패")
                    }
                }
        }


        btn_register.setOnClickListener {
            val intent=Intent(this, activity_register::class.java)
            startActivity(intent)

        }


    }
}