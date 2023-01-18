package com.example.guru2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.guru2.databinding.ActivityMainBinding

private const val ARG_URI="uri"

class InputMealFragment : Fragment() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var img_load: ImageView = view.findViewById(R.id.img_load)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val itemList = listOf("아침", "점심", "저녁", "간식")
        //val adapter = ArrayAdapter(this, R.layout.fragment_input_meal, itemList)
        //binding.spinner.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputMealFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}