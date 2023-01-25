package com.example.guru2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_input_meal.*

class MainActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginTest=findViewById<Button>(R.id.loginTest)
        val frag = findViewById<Button>(R.id.frag)

        frag.setOnClickListener {  setFrag(3) }

        fun moveToAnotherPage(){
            val intent = Intent(this,containerActivity::class.java)
            startActivity(intent)
        }

        loginTest.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        mAuth= FirebaseAuth.getInstance()
        var currentUser = mAuth?.currentUser

        //이미 로그인한적이 있는지 확인 (자동로그인)
        /*  if (currentUser == null) {

              Timer().schedule(object : TimerTask() {
                  override fun run() {
                      val intent: Intent = Intent(applicationContext, MainActivity::class.java)
                      startActivity(intent)
                      finish()
                  }
              }, 2000)

          }else{

              Timer().schedule(object : TimerTask() {
                  override fun run() {
                      val intent: Intent = Intent(applicationContext, activity_login::class.java)
                      startActivity(intent)
                      finish()
                  }
              }, 2000)

          }*/
    }

    companion object{
        private var instance:MainActivity? = null
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    override fun  onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK) {
            return
        }
        when(requestCode){
            1->{
                data?:return
                val uri=data.data as Uri
                mealImg.setImageURI(uri);
                Log.d("Load img", uri.toString())
            }

            else->{
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == RESULT_OK && it.data != null){
            var uri = it.data!!.data
            Glide.with(this).load(uri).into(mealImg)
            Log.d("Load img", uri.toString())
        }
    }

    fun changeFragment(index: Int) {


        when (index) {
            1 -> {
                val inputExerciseFragment = InputExerciseFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, inputExerciseFragment)
                    .commit()
            }

            2 -> {
                val inputMealFragment = InputMealFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, inputMealFragment)
                    .commit()
            }

            3 -> {
                val exerciseRecordFragment = ExerciseRecordFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, exerciseRecordFragment)
                    .commit()
            }

            4 -> {
                val mealRecordFragment = MealRecordFragment();
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, mealRecordFragment)
                    .commit()
            }
        }
    }

    //프래그먼트 변경 함수
    fun setFrag(fragNum: Int){
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0->{
                ft.replace(R.id.fragment_container,InputExerciseFragment()).commit()
            }
            1->{
                ft.replace(R.id.main_frame,InputMealFragment()).commit()
            }
            2->{
                ft.replace(R.id.main_frame,ExerciseRecordFragment()).commit()
            }
            3->{
                ft.replace(R.id.main_frame,MealRecordFragment()).commit()
            }

        }

    }


}