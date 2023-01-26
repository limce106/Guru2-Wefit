package com.example.guru2.Records

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.guru2.R
import com.google.android.material.tabs.TabLayout



class RecordMain : Fragment() {




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
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_record) // 탭 레이아웃
        val viewpager= view.findViewById<ViewPager>(R.id.viewpager) //뷰 페이지 불러오기

        val adapter = RecordTabAdapter(activity!!.supportFragmentManager,2) //어댑터
        viewpager.adapter=adapter //어댑터 연결

        //탭이 선택되었을 때 페이지가 바꾸도록 함
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpager.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        //탭을 이동시킬 때 같이 페이지가 이동
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))



        return view
    }


}
