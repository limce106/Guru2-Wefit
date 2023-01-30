package com.example.guru2.calender_user

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.example.guru2.Records.ExerciseRecModel
import com.example.guru2.UserListFrag
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_calender.*
import java.time.LocalDate


class Calender : Fragment() {

    var isFabOpen: Boolean = false //fab 버튼 클릭 확인용 변수
    var firestore: FirebaseFirestore? = null
    val itemList = arrayListOf<Schedule>() //아이템 배열
    val ListAdapter = RecyclerViewAdapter(itemList) //어댑터
    @RequiresApi(Build.VERSION_CODES.O)
    val todayDate: LocalDate = LocalDate.now() //오늘 날짜
    @RequiresApi(Build.VERSION_CODES.O)
    var date: String = todayDate.toString()//캘린더에서 선택한 날짜
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val indiDialog: IndividualExerciseDialog = IndividualExerciseDialog() //개인운동 팝업창
    val classDialog :ClassDialog = ClassDialog() //수업 팝업창



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_calender, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview_calender) //리사이클러 뷰 객체
        val calenderview = view.findViewById<CalendarView>(R.id.cal) //캘린더



        //새로운 데이터 저장할 때 마다 데이터 불러오기
        val databaseReference: DatabaseReference = firebaseDatabase.getReference("schedule") //db 연결
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SuspiciousIndentation")
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                itemList.clear()
                //db에서 데이터 불러오기
                dataSnapshot.children.forEach{
                    val schedule = it.getValue(Schedule::class.java)
                    schedule ?:return

                    if(schedule.date==date) //만약 선택한 날짜와 일정의 날짜가 같다면
                    itemList.add(schedule) //리사이클러뷰에 리스트 추가
                }
                //리스트가 변경됨을 어댑터에 알림
                ListAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("ExerciseRecord", databaseError.toException().toString()) // 에러문 출력
            }

        })

        recyclerview.adapter = ListAdapter //어댑터 연결
        recyclerview.layoutManager = LinearLayoutManager(activity)


        //날짜별로 클릭 시
        calenderview.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {

                date = p1.toString() + "-" + p2 + 1.toString() + "-" + p3.toString()
                var bundle = Bundle() //번들 생성
                bundle.putString("key1", date) //번들에 값
                indiDialog.arguments = bundle //값이 담긴 번들을 argunments에 담기
                classDialog.arguments = bundle //값이 담긴 번들을 argunments에 담기

                Log.d("ddd",date)



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
                classDialog.showNow(fragmentManager, "TAG_DIALOG_EVENT")
            }
        }

        //개인 운동 버튼 클릭시
        btnIndi.setOnClickListener {
            //다이얼로그 띄우기
            activity?.supportFragmentManager?.let { fragmentManager ->
                indiDialog.showNow(fragmentManager, "TAG_DIALOG_EVENT")
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
                ListAdapter.removeData(viewHolder.layoutPosition) //RecyclerViewAdapter의 removeData 함수
            }

        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(view?.findViewById<RecyclerView>(R.id.recyclerview_calender))
    }

}



