package com.example.guru2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.exerciserecord_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class ExerciseRecordMain : AppCompatActivity() {

    private var vpAdapter: FragmentStatePagerAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exerciserecord_main)

        vpAdapter = CustomPagerAdapter(supportFragmentManager)
        viewpager.adapter = vpAdapter
        viewpager.currentItem=3

    }




    class CustomPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


        private val PAGENUMBER = 7

        override fun getCount(): Int {
            return PAGENUMBER
         }



            @RequiresApi(Build.VERSION_CODES.O)
            override fun getItem(position: Int): Fragment {

                val currentDay=LocalDate.now();

                    return when (position) {

                        0 -> TestFragment.newInstance("${currentDay.minusDays(3)}")
                        1 -> TestFragment.newInstance("${currentDay.minusDays(2)}")
                        2 -> TestFragment.newInstance("${currentDay.minusDays(1)}")
                        3 -> TestFragment.newInstance("${currentDay}")
                        4 -> TestFragment.newInstance("${currentDay.plusDays(1)}")
                        5 -> TestFragment.newInstance("${currentDay.plusDays(2)}")
                        6 -> TestFragment.newInstance("${currentDay.plusDays(3)}")

                        else -> TestFragment.newInstance("")

                    }



            }



    }


}