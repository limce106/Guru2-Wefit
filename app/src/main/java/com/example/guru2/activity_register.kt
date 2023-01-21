package com.example.guru2

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView.FindListener
import android.widget.*

class activity_register : AppCompatActivity() {

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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_finish_reg=findViewById(R.id.btn_finish_reg)
        edtName=findViewById(R.id.edtName)
        edtID=findViewById(R.id.edtID)
        edtPW=findViewById(R.id.edtPW)
        re_PW=findViewById(R.id.re_PW)
        edtTEL=findViewById(R.id.edtTEL)
        rb_gender=findViewById(R.id.rb_gender)
        male=findViewById(R.id.male)
        female=findViewById(R.id.female)
        rb_purpose=findViewById(R.id.rb_purpose)
        rb_P1=findViewById(R.id.rb_P1)
        rb_P2=findViewById(R.id.rb_P2)
        rb_P3=findViewById(R.id.rb_P3)
        rb_P4=findViewById(R.id.rb_P4)

        dbManager= DBManager(this, "memberDB", null, 1)

        btn_finish_reg.setOnClickListener{
            var str_name:String=edtName.text.toString()
            var str_ID:String=edtID.text.toString()
            var str_PW:String=""


            //비밀번호와 비밀번호 확인이 같으면 넣는다
            if(edtPW.text.toString()==re_PW.text.toString()){
                str_PW=edtPW.text.toString()
            }

            var str_Tel:String=edtTEL.text.toString()
            var str_gender:String=""
            if(rb_gender.checkedRadioButtonId==R.id.male){
                str_gender=male.text.toString()
            }
            if(rb_gender.checkedRadioButtonId==R.id.female){
                str_gender=female.text.toString()
            }

            var str_purpose:String=""
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

            //모든 정보가 채워져야 회원가입 성공
            if(str_name.isBlank()||str_ID.isBlank()||str_PW.isBlank()||str_Tel.isBlank()||
                str_gender.isBlank()||str_purpose.isBlank()){
                Toast.makeText(applicationContext, "모든 빈칸을 채워주세요", Toast.LENGTH_SHORT).show()
            }

            sqlitedb=dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO members VALUES ('"  +
                str_name+"','" +str_ID+"', '"+str_PW+"',"+ str_Tel+"', '"+str_gender+"','"+str_purpose+"')")

            sqlitedb.close()

            // 넘길 화면 > val intent=Intent
        }
    }
}














