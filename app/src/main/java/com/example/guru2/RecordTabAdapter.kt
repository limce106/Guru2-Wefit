package com.example.guru2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.firestore.v1.StructuredAggregationQuery.Aggregation.Count

class RecordTabAdapter(fragmentManager:FragmentManager,val tabCount: Int): FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> ExerciseRecordFragment()
            1-> MealRecordFragment()
            else -> return ExerciseRecordFragment()
        }
    }


}