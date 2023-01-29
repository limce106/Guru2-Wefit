package com.example.guru2.graph_user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.guru2.GraphInputInbody
import com.example.guru2.R
import com.example.guru2.calender_user.ClassDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_graph.*
import kotlinx.android.synthetic.main.fragment_graph_input_inbody.*


class Graph : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //객체 생성
        val view = inflater.inflate(R.layout.fragment_graph, container, false)
        val viewpager= view.findViewById<ViewPager>(R.id.viewpager_graph) //뷰 페이지 불러오기
        val adapter = GraphViewPagerAdapter(requireActivity().supportFragmentManager) //어댑터
        val btn_inbody_add = view.findViewById<FloatingActionButton>(R.id.btn_inbody_add) //인바디 기록 추가 버튼
        val dialog: GraphInputInbody = GraphInputInbody().getInstance() //인바디 입력 팝업창

        viewpager.adapter= adapter //어댑터 연결
        viewpager.currentItem=4


        //인바디 기록 추가 버튼 클릭 이벤트
        btn_inbody_add.setOnClickListener {
            activity?.supportFragmentManager?.let { fragmentManager ->
                dialog.show(fragmentManager, "TAG_DIALOG_EVENT")
            }
        }

        return view

    }


    class GraphViewPagerAdapter(fm: FragmentManager):FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        private val PAGENUMBER = 4 //뷰 페이저 수

        override fun getCount(): Int{
            return PAGENUMBER
        }

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> GraphViewPager.newInstance("22.10.02","51.3","15.9","36.4")
                1-> GraphViewPager.newInstance("22.11.03","50.3","15.7","34.4")
                2-> GraphViewPager.newInstance("22.12.06","47.3","15.7","33.4")
                3-> GraphViewPager.newInstance("23.01.12","46.3","15.7","32.4")
                else -> GraphViewPager.newInstance("기록없음","00","00","00")

            }
        }
    }




}