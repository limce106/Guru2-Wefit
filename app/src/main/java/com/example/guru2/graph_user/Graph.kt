package com.example.guru2.graph_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.guru2.R


class Graph : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_graph, container, false)
        val viewpager= view.findViewById<ViewPager>(R.id.viewpager_graph) //뷰 페이지 불러오기
        val adapter = GraphViewPagerAdapter(requireActivity().supportFragmentManager) //어댑터

        viewpager.adapter= adapter //어댑터 연결

        return view
    }

    class GraphViewPagerAdapter(fm: FragmentManager):FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        private val PAGENUMBER = 4 //뷰 페이저 수

        override fun getCount(): Int{
            return PAGENUMBER
        }

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> GraphViewPager.newInstance("51.3","15.9","36.4")
                1-> GraphViewPager.newInstance("50.3","15.7","34.4")
                2-> GraphViewPager.newInstance("47.3","15.7","33.4")
                3-> GraphViewPager.newInstance("46.3","15.7","32.4")
                else -> GraphViewPager.newInstance("기록 없음","기록 없음","기록 없음")
            }
        }
    }


}