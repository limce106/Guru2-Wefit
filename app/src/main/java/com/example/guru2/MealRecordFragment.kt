package com.example.guru2

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_exercise_record.*

class MealRecordFragment : Fragment() {
    private var text: String? = null

    private lateinit var RVMealRecadapter:RecyclerAdapterMeal
    @RequiresApi(Build.VERSION_CODES.O)
    var ref = FirebaseDatabase.getInstance().getReference().child("MealRecord").child("NickName")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString("text", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_meal_record, container, false)
        val list = ArrayList<MealRecModel>()

        val rv_mealRecord: RecyclerView = rootView.findViewById(R.id.rv_exerciseRecord)
        RVMealRecadapter = RecyclerAdapterMeal(list)
        rv_mealRecord.adapter = RVMealRecadapter
        RVMealRecadapter.notifyDataSetChanged()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseDate.text = text

    }

    companion object {
        @JvmStatic
        fun newInstance(text1: String) =
            MealRecordFragment().apply {
                arguments = Bundle().apply {
                    putString("text", text1)
                }
            }
    }
}