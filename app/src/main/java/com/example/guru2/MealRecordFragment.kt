package com.example.guru2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_input_exercise.*

class MealRecordFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_meal_record, container, false)
        val list = ArrayList<MealRecModel>()

        var timeSlot = arguments?.getString("timeSlot")
        var mealName = arguments?.getString("mealName")
        var isSaveData = arguments?.getBoolean("isSaveData")
        if(isSaveData == true)
        {
            list.add(MealRecModel(timeSlot.toString(), mealName.toString()))
        }

        val rv_mealRecord: RecyclerView = rootView.findViewById(R.id.rv_mealRecord)
        val RVMealRecadapter = RecyclerAdapterMeal(list)
        rv_exerciseName.adapter = RVMealRecadapter

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MealRecordFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}