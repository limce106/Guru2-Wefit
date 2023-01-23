package com.example.guru2

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView.FindListener
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class activity_register : AppCompatActivity() {

    lateinit var mAuth:FirebaseAuth

    private val TAG:String=activity_register::class.java.simpleName

    lateinit var dbManager: DBManager
    lateinit var sqlitedb:SQLiteDatabase

    lateinit var btn_finish_reg:Button
    lateinit var edtName:EditText
    lateinit var edtID:EditText
    lateinit var edtPW:EditText
    lateinit var re_PW:EditText
    lateinit var edtTEL:EditText
    lateinit var rb_gender:RadioGroup
    lateinit var male:RadioButton
    lateinit var female:RadioButton
    lateinit var rb_purpose:RadioGroup
    lateinit var rb_P1:RadioButton
    lateinit var rb_P2:RadioButton
    lateinit var rb_P3:RadioButton
    lateinit var rb_P4:RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {

        edtID=findViewById(R.id.edtID)
        edtPW=findViewById(R.id.edtPW)

        val reg_id=edtID.text.toString()
        val reg_pw=edtPW.text.toString()

        //인증 초기화
        mAuth= Firebase.auth

        btn_finish_reg.setOnClickListener {

            if(edtID.text.isEmpty()||edtPW.text.isEmpty()){
                Toast.makeText(this, "모든 항목을 채워주세요", Toast.LENGTH_SHORT)
                    .show()
            }

            signUp(reg_id,reg_pw)

        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }


        //회원가입
        private fun signUp (reg_id:String, reg_pw:String){


            mAuth.createUserWithEmailAndPassword(reg_id,reg_pw)
                .addOnCompleteListener(this){ task->
                    if(task.isSuccessful){
                    //성공 시 실행
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                    //실패 시 실행행
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }






    }















