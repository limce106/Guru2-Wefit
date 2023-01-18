package com.example.guru2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: FragmentManager) : FragmentManager(){
    fun getItemCount(): Int = 2

    fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExerciseRecordFragment()
            else -> MealRecordFragment()
        }
    }
}