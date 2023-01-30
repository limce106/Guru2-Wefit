package com.example.guru2.calender_user

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_calender.*


class Calender : Fragment() {

    var isFabOpen: Boolean = false //fab 버튼 클릭 확인용 변수
    var firestore: FirebaseFirestore? = null
    val itemList = arrayListOf<Schedule>() //아이템 배열
    val ListAdapter = RecyclerViewAdapter(itemList) //어댑터
    var date: String = "" //캘린더에서 선택한 날짜
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.getReference()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_calender, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview_calender) //리사이클러 뷰 객체
        val calenderview = view.findViewById<CalendarView>(R.id.cal) //캘린더
        firestore = FirebaseFirestore.getInstance() //파이어스토어 인스턴스 초기화

        val indiDialog: IndividualExerciseDialog = IndividualExerciseDialog()

        val bundle = Bundle() //번들 생성
        bundle.putString("key1", date) //번들에 값 담기
        indiDialog.arguments = bundle //값이 담긴 번들을 argunments에 담기

        recyclerview.adapter = ListAdapter //어댑터 연결
        recyclerview.layoutManager = LinearLayoutManager(activity)

        //아이템 추가
        itemList.add(Schedule("2023/01/02", "개인 운동", "12:00"))
        itemList.add(Schedule("2023/01/02", "개인 운동", "15:00"))
        itemList.add(Schedule("2023/01/02", "PT 수업", "16:00"))
        itemList.add(Schedule("2023/01/02", "PT 수업", "18:00"))

        //리스트가 변경됨을 어댑터에 알림
        ListAdapter.notifyDataSetChanged()

        calenderview.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                //ListAdapter.setList()
                date = p1.toString() + "/" + p2 + 1.toString() + "/" + p3.toString()
                Log.d("hhh", date)
                Log.d("kkk", bundle.toString())
                Log.d("kkt", indiDialog.arguments.toString())
            }
        })


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
        val dialog: ClassDialog = ClassDialog().getInstance() //수업 예약 팝업창
        val dialog2: IndividualExerciseDialog = IndividualExerciseDialog().getInstance() //개인 운동 팝업창




        textClass.bringToFront() //텍스트 맨 앞으로
        textIndi.bringToFront() //텍스트 맨 앞으로

        //일정 추가하기 버튼 클릭시
        btnAdd.setOnClickListener {

            if (isFabOpen) //플로팅액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션 세팅
            {
                btnClass.visibility = View.INVISIBLE
                btnIndi.visibility = View.INVISIBLE
                textClass.visibility = View.INVISIBLE
                textIndi.visibility = View.INVISIBLE
                ObjectAnimator.ofFloat(btnIndi, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(btnClass, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(textClass, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(textIndi, "translationY", 0f).apply { start() }
                btnAdd.setImageResource(R.drawable.ic_baseline_add_24)
            } else if (!isFabOpen)  //플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션 세팅
            {
                btnClass.visibility = View.VISIBLE
                btnIndi.visibility = View.VISIBLE
                textClass.visibility = View.VISIBLE
                textIndi.visibility = View.VISIBLE
                ObjectAnimator.ofFloat(btnIndi, "translationY", -200f).apply { start() }
                ObjectAnimator.ofFloat(btnClass, "translationY", -350f).apply { start() }
                ObjectAnimator.ofFloat(textIndi, "translationY", -200f).apply { start() }
                ObjectAnimator.ofFloat(textClass, "translationY", -300f).apply { start() }
                btnAdd.setImageResource(R.drawable.ic_baseline_clear_24)
            }
            isFabOpen = !isFabOpen
        }

        //수업 예약하기 버튼 클릭시
        btnClass.setOnClickListener {
            //다이얼로그 띄우기
            activity?.supportFragmentManager?.let { fragmentManager ->
                dialog.show(fragmentManager, "TAG_DIALOG_EVENT")
            }
        }

        //개인 운동 버튼 클릭시
        btnIndi.setOnClickListener {
            //다이얼로그 띄우기
            activity?.supportFragmentManager?.let { fragmentManager ->
                dialog2.show(fragmentManager, "TAG_DIALOG_EVENT")
            }

        }

        itemTouch()//아이템 삭제 실행


    }

    //아이템 삭제를 위한 함수
    fun itemTouch() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                ListAdapter.removeData(viewHolder.layoutPosition)
            }

        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(view?.findViewById<RecyclerView>(R.id.recyclerview_calender))
    }

}