package com.example.guru2

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton


class calender : Fragment() {

    var isFabOpen : Boolean = false //fab 버튼 클릭 확인용 변수
    var subBtnOn : Boolean = false //서브 버튼들 활성화 확인용 변수

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calender, container, false)
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.btn_add) //일정 추가하기 버튼
        val btnClass = view.findViewById<FloatingActionButton>(R.id.btn_class)//수업 예약 버튼
        val btnIndi = view.findViewById<FloatingActionButton>(R.id.btn_indi)//개인 운동 버튼
        val textClass = view.findViewById<TextView>(R.id.text_class)//수업 예약 글씨
        val textIndi = view.findViewById<TextView>(R.id.text_indi)//개인 운동 글씨

        btnAdd.setOnClickListener{

            if(isFabOpen) //플로팅액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션 세팅
            {
                btnClass.visibility= View.INVISIBLE
                btnIndi.visibility=View.INVISIBLE
                textClass.visibility=View.INVISIBLE
                textIndi.visibility=View.INVISIBLE
                ObjectAnimator.ofFloat(btnIndi,"translationY",0f).apply{start()}
                ObjectAnimator.ofFloat(btnClass,"translationY",0f).apply{start()}
                ObjectAnimator.ofFloat(textClass,"translationY",0f).apply{start()}
                ObjectAnimator.ofFloat(textIndi,"translationY",0f).apply{start()}
                btnAdd.setImageResource(R.drawable.ic_baseline_add_24)
            }
            else if(!isFabOpen)  //플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션 세팅
            {

                btnClass.visibility= View.VISIBLE
                btnIndi.visibility=View.VISIBLE
                textClass.visibility=View.VISIBLE
                textIndi.visibility=View.VISIBLE
                ObjectAnimator.ofFloat(btnIndi, "translationY", -200f).apply { start() }
                ObjectAnimator.ofFloat(btnClass, "translationY", -400f).apply { start() }
                ObjectAnimator.ofFloat(textIndi, "translationY", -200f).apply { start() }
                ObjectAnimator.ofFloat(textClass, "translationY", -350f).apply { start() }
                btnAdd.setImageResource(R.drawable.ic_baseline_clear_24)
            }
            isFabOpen = !isFabOpen
        }

        return view
    }



}