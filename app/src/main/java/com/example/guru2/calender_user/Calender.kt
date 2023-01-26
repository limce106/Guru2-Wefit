package com.example.guru2.calender_user

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_calender.*


class Calender : Fragment() {

    var isFabOpen : Boolean = false //fab 버튼 클릭 확인용 변수
    var firestore : FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_calender, container, false)
        val recyclerview=view.findViewById<RecyclerView>(R.id.recyclerview)
        firestore =  FirebaseFirestore.getInstance() //파이어스토어 인스턴스 초기화
        val itemList = arrayListOf<Schedule>() //아이템 배열
        val ListAdapter = RecyclerViewAdapter(itemList) //어댑터

        recyclerview.adapter= ListAdapter //어댑터 연결
        recyclerview.layoutManager=LinearLayoutManager(activity)

        //아이템 추가
        itemList.add(Schedule("12","13"))
        itemList.add(Schedule("13","14"))
        itemList.add(Schedule("13","14"))
        itemList.add(Schedule("13","14"))

        //리스트가 변경됨을 어댑터에 알림
        ListAdapter.notifyDataSetChanged()

        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //객체 생성
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.btn_add) //일정 추가하기 버튼
        val btnClass = view.findViewById<FloatingActionButton>(R.id.btn_class)//수업 예약 버튼
        val btnIndi = view.findViewById<FloatingActionButton>(R.id.btn_indi)//개인 운동 버튼
        val textClass = view.findViewById<TextView>(R.id.text_class)//수업 예약 글씨
        val textIndi = view.findViewById<TextView>(R.id.text_indi)//개인 운동 글씨
        val cal = view.findViewById<CalendarView>(R.id.cal) //캘린더
        val dialog: ClassDialog = ClassDialog().getInstance() //수업 예약 팝업창
        val dialog2: IndividualExerciseDialog = IndividualExerciseDialog().getInstance() //개인 운동 팝업창


        textClass.bringToFront()
        textIndi.bringToFront()

        //일정 추가하기 버튼 클릭시
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
                ObjectAnimator.ofFloat(btnClass, "translationY", -350f).apply { start() }
                ObjectAnimator.ofFloat(textIndi, "translationY", -200f).apply { start() }
                ObjectAnimator.ofFloat(textClass, "translationY", -300f).apply { start() }
                btnAdd.setImageResource(R.drawable.ic_baseline_clear_24)
            }
            isFabOpen = !isFabOpen
        }

        //수업 예약하기 버튼 클릭시
        btnClass.setOnClickListener{
            //다이얼로그 띄우기
            activity?.supportFragmentManager?.let {fragmentManager ->
                dialog.show(fragmentManager,"TAG_DIALOG_EVENT")
            }
        }

        //개인 운동 버튼 클릭시
        btnIndi.setOnClickListener{
            //다이얼로그 띄우기
            activity?.supportFragmentManager?.let {fragmentManager ->
                dialog2.show(fragmentManager,"TAG_DIALOG_EVENT")
            }
        }



    }

}