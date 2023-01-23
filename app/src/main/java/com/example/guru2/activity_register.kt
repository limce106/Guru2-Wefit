package com.example.guru2

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView.FindListener
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class activity_register : AppCompatActivity() {

    lateinit var mAuth:FirebaseAuth

    private lateinit var mDbRef: DatabaseReference

    private val TAG:String=activity_register::class.java.simpleName



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
        edtName=findViewById(R.id.edtName)
        edtTEL=findViewById(R.id.edtTEL)
        rb_gender=findViewById(R.id.rb_gender)
        male=findViewById(R.id.male)
        female=findViewById(R.id.female)
        rb_purpose=findViewById(R.id.rb_purpose)
        rb_P1=findViewById(R.id.rb_P1)
        rb_P2=findViewById(R.id.rb_P2)
        rb_P3=findViewById(R.id.rb_P3)
        rb_P4=findViewById(R.id.rb_P4)

        val reg_id=edtID.text.toString()
        val reg_pw=edtPW.text.toString()
        val reg_name=edtName.text.toString()
        val reg_tel=edtTEL.text.toString()
        var str_gender:String=""
        var str_purpose:String=""

        if(rb_gender.checkedRadioButtonId==R.id.male){
            str_gender=male.text.toString()
        }
        if(rb_gender.checkedRadioButtonId==R.id.female){
            str_gender=female.text.toString()
        }


        if(rb_purpose.checkedRadioButtonId==R.id.rb_P1){
            str_purpose=rb_P1.text.toString()

        }
        if(rb_purpose.checkedRadioButtonId==R.id.rb_P2){
            str_purpose=rb_P2.text.toString()

        }
        if(rb_purpose.checkedRadioButtonId==R.id.rb_P3){
            str_purpose=rb_P3.text.toString()

        }
        if(rb_purpose.checkedRadioButtonId==R.id.rb_P4){
            str_purpose=rb_P4.text.toString()

        }

        //인증 초기화
        mAuth= Firebase.auth

        //DB 초기화
        mDbRef=Firebase.database.reference

        btn_finish_reg.setOnClickListener {


            if(edtID.text.isEmpty()||edtPW.text.isEmpty()){
                Toast.makeText(this, "모든 항목을 채워주세요", Toast.LENGTH_SHORT)
                    .show()
            }

            signUp(reg_id,reg_pw,reg_name, reg_tel,str_gender, str_purpose)

        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }


        //회원가입
        private fun signUp (reg_id:String, reg_pw:String, reg_name:String,
                            reg_tel:String, str_gender:String, str_purpose:String){


            mAuth.createUserWithEmailAndPassword(reg_id,reg_pw)
                .addOnCompleteListener(this){ task->
                    if(task.isSuccessful){
                    //성공 시 실행
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        addUserToDatabase(reg_id, reg_pw, reg_name, reg_tel,
                            str_gender, str_purpose, mAuth.currentUser?.uid!!)
                    }else{
                    //실패 시 실행행
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        Log.d("SignUp", "Error : ${task.exception}")
                    }
                }
        }


        private fun addUserToDatabase(reg_id:String, reg_pw:String, reg_name:String,
                                      reg_tel:String, str_gender:String, str_purpose:String, uId:String)
        {
            mDbRef.child("user").child(uId).setValue(User(reg_id, reg_pw, reg_name, reg_tel, str_gender, str_purpose,uId))

        }
    }















