package com.example.guru2.calender_trainer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.example.guru2.calender_user.IndividualExerciseDialog
import com.example.guru2.calender_user.RecyclerViewAdapter
import com.example.guru2.calender_user.Schedule
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_calender.*


class CalenderTrainer : Fragment() {


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

        val view = inflater.inflate(R.layout.fragment_calender_trainer, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview_calender_trainer) //리사이클러 뷰 객체
        val calenderview = view.findViewById<CalendarView>(R.id.cal_trainer) //캘린더
        firestore = FirebaseFirestore.getInstance() //파이어스토어 인스턴스 초기화


        recyclerview.adapter = ListAdapter //어댑터 연결
        recyclerview.layoutManager = LinearLayoutManager(activity)

        //아이템 추가
        itemList.add(Schedule("2023/01/02", "그룹 PT", "12:00"))
        itemList.add(Schedule("2023/01/02", "개인 PT", "15:00"))
        itemList.add(Schedule("2023/01/02", "그룹 PT", "16:00"))
        itemList.add(Schedule("2023/01/02", "개인 PT", "18:00"))

        //리스트가 변경됨을 어댑터에 알림
        ListAdapter.notifyDataSetChanged()

        calenderview.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                //ListAdapter.setList()
                date = p1.toString() + "/" + p2 + 1.toString() + "/" + p3.toString()
                Log.d("hhh", date)
            }
        })


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //객체 생성
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.btn_add_class) //일정 추가하기 버튼


        //일정 추가하기 버튼 클릭시
        btnAdd.setOnClickListener {

            val trainerDialog: TrainerCalenderDialog = TrainerCalenderDialog()
            val bundle = Bundle() //번들 생성
            bundle.putString("key2", date) //번들에 값 담기
            trainerDialog.arguments = bundle //값이 담긴 번들을 argunments에 담기

            val dialog: TrainerCalenderDialog = TrainerCalenderDialog().getInstance() //수업 예약 팝업창

            //다이얼로그 띄우기
            activity?.supportFragmentManager?.let { fragmentManager ->
                dialog.show(fragmentManager, "TAG_DIALOG_EVENT")
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
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(view?.findViewById<RecyclerView>(R.id.recyclerview_calender_trainer))
    }

}