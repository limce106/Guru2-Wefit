package com.example.guru2

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton


class schedule : AppCompatActivity() {


    var isFabOpen : Boolean = false //fab 버튼 클릭 확인용 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val btnAdd = findViewById<FloatingActionButton>(R.id.btn_add) //일정 추가하기 버튼
        val btnClass = findViewById<FloatingActionButton>(R.id.btn_class)//수업 예약 버튼
        val btnIndi = findViewById<FloatingActionButton>(R.id.btn_indi)//개인 운동 버튼
        val textClass = findViewById<TextView>(R.id.text_class)//수업 예약 글씨
        val textIndi = findViewById<TextView>(R.id.text_indi)//개인 운동 글씨
        var subBtnOn : Boolean = false //서브 버튼들 활성화 확인용 변수



        btnAdd.setOnClickListener{

            if(!subBtnOn) //서브 버튼들이 활성화 안 되어있다면
            {
                btnClass.visibility= View.VISIBLE
                btnIndi.visibility=View.VISIBLE
                textClass.visibility=View.VISIBLE
                textIndi.visibility=View.VISIBLE
                subBtnOn=true
            }
            else if(subBtnOn) //서브 버튼들이 활성화 되어있다면
            {
                btnClass.visibility= View.INVISIBLE
                btnIndi.visibility=View.INVISIBLE
                textClass.visibility=View.INVISIBLE
                textIndi.visibility=View.INVISIBLE
                subBtnOn=false
            }

            //플로팅 버튼 클릭시 에니메이션 동작 기능
            toggleFab()
        }

    }

    //fab 클릭시 동작하는 애니메이션 효과 세팅
    fun toggleFab()
    {
        val btnAdd = findViewById<FloatingActionButton>(R.id.btn_add) //일정 추가하기 버튼
        val btnClass = findViewById<FloatingActionButton>(R.id.btn_class)//수업 예약 버튼
        val btnIndi = findViewById<FloatingActionButton>(R.id.btn_indi)//개인 운동 버튼
        val textClass = findViewById<TextView>(R.id.text_class)//수업 예약 글씨
        val textIndi = findViewById<TextView>(R.id.text_indi)//개인 운동 글씨

        Toast.makeText(this,"메인 플로팅 버튼 클릭: $isFabOpen", Toast.LENGTH_SHORT).show()

        //플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션 세팅
        if(isFabOpen){
            ObjectAnimator.ofFloat(btnIndi,"translationY",0f).apply{start()}
            ObjectAnimator.ofFloat(btnClass,"translationY",0f).apply{start()}
            ObjectAnimator.ofFloat(textClass,"translationY",0f).apply{start()}
            ObjectAnimator.ofFloat(textIndi,"translationY",0f).apply{start()}
            btnAdd.setImageResource(R.drawable.ic_baseline_add_24)
        }//플로팅액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션 세팅
        else{
            ObjectAnimator.ofFloat(btnIndi, "translationY", -200f).apply { start() }
            ObjectAnimator.ofFloat(btnClass, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(textIndi, "translationY", -200f).apply { start() }
            ObjectAnimator.ofFloat(textClass, "translationY", -350f).apply { start() }
            btnAdd.setImageResource(R.drawable.ic_baseline_clear_24)
        }

        isFabOpen = !isFabOpen

    }





}