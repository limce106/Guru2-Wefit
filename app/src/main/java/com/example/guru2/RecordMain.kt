package com.example.guru2

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.time.LocalDate


class RecordMain : Fragment() {

    private var vpAdapter: FragmentStatePagerAdapter? = null
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_record_main, container, false)
        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout) // 탭 레이아웃
        val viewpager= view.findViewById<ViewPager>(R.id.viewpager) //뷰 페이지 불러오기
        val adapter = CustomerPagerAdapter(activity!!.supportFragmentManager) //어댑터

        viewpager.adapter=adapter //어댑터 연결

        return view
    }


    class CustomerPagerAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

        private val PAGENUMBER=4 //뷰 페이지 수

        override fun getCount(): Int {
            return PAGENUMBER
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun getItem(position: Int): Fragment {

            val currentDay= LocalDate.now(); //현재 날짜 변수


           return when(position){

               0 -> ExerciseRecordFragment.newInstance("${currentDay.minusDays(3)}")
//                1 -> ExerciseRe_frag.newInstance("${currentDay.minusDays(2)}")
//                2 -> ExerciseRe_frag.newInstance("${currentDay.minusDays(1)}")
//                3 -> ExerciseRe_frag.newInstance("${currentDay}")
//                4 -> ExerciseRe_frag.newInstance("${currentDay.plusDays(1)}")
//                5 -> ExerciseRe_frag.newInstance("${currentDay.plusDays(2)}")
//                6 -> ExerciseRe_frag.newInstance("${currentDay.plusDays(3)}")

               else -> ExerciseRecordFragment.newInstance("")
           }
        }

    }

}


