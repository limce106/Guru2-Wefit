package com.example.guru2.graph_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guru2.R
import kotlinx.android.synthetic.main.fragment_graph_view_pager.*


class GraphViewPager : Fragment() {

    private var weight : String? = null //몸무게 텍스트
    private var muscle : String? = null //골격근량 텍스트
    private var bodyfat : String? = null //체지방률 텍스트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weight = it.getString("weight","")
            muscle = it.getString("muscle","")
            bodyfat = it.getString("bodyfat","")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graph_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //text_weight.text = weight
        //text_muscle.text = muscle
       // text_bodyfat.text = bodyfat
    }

    companion object{
        fun newInstance(weight:String, muscle:String,bodyfat:String) =
            GraphViewPager().apply{
                arguments = Bundle().apply{
                    putString("weight",weight)
                    putString("muscle",muscle)
                    putString("bodyfat",bodyfat)
                }
            }

    }


}