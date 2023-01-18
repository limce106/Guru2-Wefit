package com.example.guru2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.guru2.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecordLists : Fragment() {
    val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
    lateinit var binding: ActivityMainBinding
    private lateinit var exerciseFab: FloatingActionButton
    private lateinit var mealFab: FloatingActionButton
    private lateinit var pager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        /*arguments?.let {
            var binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // 뷰페이저에 어댑터 연결
            binding.pager.adapter = ViewPagerAdapter(this)

            *//* 탭과 뷰페이저를 연결, 여기서 새로운 탭을 다시 만드므로 레이아웃에서 꾸미지말고
            여기서 꾸며야함
             *//*
            TabLayoutMediator(binding.tabLayout, binding.pager) {tab, position ->
                when(position) {
                    0 -> tab.text = "운동 기록"
                    else -> tab.text = "식단 기록"
                }
            }.attach()

            var isOpen = false

            val mainFabClickListener: View.OnClickListener = View.OnClickListener {
                if(!isOpen) { // fab이 눌리지 않았다면
                    ObjectAnimator.ofFloat(exerciseFab, "translationY", -400f).apply { start() }
                    ObjectAnimator.ofFloat(mealFab, "translationY", -200f).apply { start() }
                    isOpen = true
                }
                else{ // fab이 눌렸다면
                    ObjectAnimator.ofFloat(exerciseFab, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(mealFab, "translationY", 0f).apply { start() }
                    isOpen = false
                }
            }

            val exerciseFabClickListener: View.OnClickListener = View.OnClickListener {
                startActivity(Intent(this, ExerciseRecordFragment::class.java))
            }

            val mealFabClickListener: View.OnClickListener = View.OnClickListener {
                startActivity(Intent(this, MealRecordFragment::class.java))
            }
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_lists, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecordLists().apply {
                arguments = Bundle().apply {

                }
            }
    }
}