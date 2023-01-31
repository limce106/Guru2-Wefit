package com.example.guru2.calender_user

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.NaviActivity
import com.example.guru2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_calender.*
import java.time.LocalDate


class Calender : Fragment() {

    var isFabOpen: Boolean = false //fab 버튼 클릭 확인용 변수
    var firestore: FirebaseFirestore? = null
    val itemList = arrayListOf<Schedule>() //아이템 배열
    lateinit var uidList: ArrayList<String>
    lateinit var recyclerView: RecyclerView
    lateinit var ListAdapter: RecyclerView.Adapter<*> //어댑터
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
        val mActivity = activity as NaviActivity


        //새로운 데이터 저장할 때 마다 데이터 불러오기
        val databaseReference: DatabaseReference = firebaseDatabase.getReference("schedule").child(mActivity.loginUser()!!) //db 연결
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
                //db에서 데이터 불러오기
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                itemList.clear() // 기존 배열리스트가 존재하지않게 초기화
                uidList.clear()
                for (snapshot in dataSnapshot.children) { // 반복문으로 데이터 List를 추출해냄
                    val schedule: Schedule =
                        snapshot.getValue(Schedule::class.java)!! // 만들어뒀던 객체에 데이터를 담는다.
                    val uidKey: String = snapshot.key.toString()
                    itemList.add(schedule) // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    uidList.add(uidKey)
                }
                ListAdapter.notifyDataSetChanged() // 리스트 저장 및 새로고침해야 반영이 됨
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("Calender", databaseError.toException().toString()) // 에러문 출력
            }

        })

        val ct: Context = container!!.context
        ListAdapter = RecyclerViewAdapter(itemList, ct, uidList)
        recyclerView.adapter = ListAdapter

        //recyclerview.adapter = ListAdapter //어댑터 연결
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
        //itemTouch()//아이템 삭제 실행
    }

    //아이템 삭제를 위한 함수
//    fun itemTouch() {
//        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
//            ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT
//        ) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                ListAdapter.removeData(viewHolder.layoutPosition) //RecyclerViewAdapter의 removeData 함수
//            }
//
//        }
//        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(view?.findViewById<RecyclerView>(R.id.recyclerview_calender))
//    }

}



