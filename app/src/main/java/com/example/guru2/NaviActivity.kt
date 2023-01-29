package com.example.guru2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.guru2.Recommend.Trainer_Recommend_Fragment
import com.example.guru2.Records.ExerciseRecordFragment
import com.example.guru2.calender_user.Calender
import com.example.guru2.databinding.ActivityNaviBinding
import com.example.guru2.graph_user.Graph
import kotlinx.android.synthetic.main.activity_navi.*


private const val TAG_RECOMMEND = "recommend_fragment" //운동 추천 프래그먼트
private const val TAG_RECORD = "record_fragment" //운동 및 식단 기록 프래그먼트
private const val TAG_CALENDAR = "calender_fragment" //일정 예약 프래그먼트
private const val TAG_GRAPH = "graph_fragment" //변화 프래그먼트
private const val TAG_MESSAGE = "message_fragment" //메세지 프래그먼트



class NaviActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNaviBinding
    lateinit var recordfragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_CALENDAR, Calender()) //시작 프래그먼트
        bottomNavigationView.selectedItemId = R.id.calendarFragment


        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                //각 프래그먼트 연결하기
                R.id.recommendFragment -> setFragment(TAG_RECOMMEND, Trainer_Recommend_Fragment())
                R.id.recordFragment-> setFragment(TAG_RECORD, ExerciseRecordFragment())
                R.id.calendarFragment -> setFragment(TAG_CALENDAR, Calender())
                R.id.graphFragment -> setFragment(TAG_GRAPH, Graph())
                R.id.messageFragment -> setFragment(TAG_MESSAGE,Chat())
            }
            true
        }
    }

    companion object{
        private var instance:NaviActivity = NaviActivity()
        fun getInstance(): NaviActivity {
            return instance
        }
    }

    //프래그먼트 세팅
    fun setFragment(tag:String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransition = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransition.add(R.id.main_frame, fragment, tag)
        }

        val recommend = manager.findFragmentByTag(TAG_RECOMMEND)
        val record = manager.findFragmentByTag(TAG_RECORD)
        val calender = manager.findFragmentByTag(TAG_CALENDAR)
        val graph = manager.findFragmentByTag(TAG_GRAPH)
        val message = manager.findFragmentByTag(TAG_MESSAGE)

        if (recommend != null) {
            fragTransition.hide(recommend)
        }
        if (record != null) {
            fragTransition.hide(record)
        }
        if (calender != null) {
            fragTransition.hide(calender)
        }
        if (graph != null) {
            fragTransition.hide(graph)
        }
        if (message != null) {
            fragTransition.hide(message)
        }


        if (tag == TAG_RECOMMEND) {
            if (recommend != null) {
                fragTransition.show(recommend)
            }
        } else if (tag == TAG_RECORD) {
            if (record != null) {
                    fragTransition.show(record)
            }
        } else if (tag == TAG_CALENDAR) {
            if (calender != null) {
                fragTransition.show(calender)
            }
        }else if (tag == TAG_GRAPH) {
            if (graph != null) {
                fragTransition.show(graph)
            }
        } else if (tag == TAG_MESSAGE) {
            if (message != null) {
                fragTransition.show(message)
            }
        }

            fragTransition.commitAllowingStateLoss()

        }


    //프래그먼트 교체 함수
    fun replaceRecord(fragment: Fragment){

        //기록 프래그먼트 새로 불러오기
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,fragment, TAG_RECORD).commit()

    }

    }
