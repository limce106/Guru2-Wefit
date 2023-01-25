package com.example.guru2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MealRecordFragment : Fragment() {
    private var text: String? = null
    private lateinit var auth: FirebaseAuth

    private lateinit var RVMealRecadapter:RecyclerAdapterMeal

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

        val rv_mealRecord: RecyclerView = rootView.findViewById(R.id.rv_mealRecord)
        RVMealRecadapter = RecyclerAdapterMeal(list)
        rv_mealRecord.adapter = RVMealRecadapter
        RVMealRecadapter.notifyDataSetChanged()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

    }
}