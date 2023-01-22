package com.example.guru2

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView.FindListener
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class activity_register : AppCompatActivity() {


    private lateinit var auth:FirebaseAuth
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

        auth=FirebaseAuth.getInstance()



        btn_finish_reg.setOnClickListener {

            if(edtID.text.isEmpty()||edtPW.text.isEmpty()){
                Toast.makeText(this, "모든 항목을 채워주세요", Toast.LENGTH_SHORT)
                    .show()
            }

            auth.createUserWithEmailAndPassword(reg_id,reg_pw)
                .addOnCompleteListener(this){ task->
                    if(task.isSuccessful){

                        Log.d(TAG, "성공")
                    }else{
                        Log.d(TAG, "실패")
                    }
                }
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



    }
}














