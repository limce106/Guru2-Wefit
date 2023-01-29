package com.example.guru2

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.guru2.calender_user.ClassDialog
import com.example.guru2.calender_user.IndividualItem
import com.example.guru2.graph_user.InbodyItem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_graph_input_inbody.*


class GraphInputInbody : DialogFragment() {

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = firebaseDatabase.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_graph_input_inbody, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //다이얼로그 배경 투명하게
        return view
    }


    fun getInstance(): GraphInputInbody {
        return GraphInputInbody()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_inbody_add.setOnClickListener{

            val weight = input_weight.text.toString()
            val muscle = input_muscle.text.toString()
            val bodyfat = input_bodyfat.text.toString()

            val dataInput= InbodyItem("$weight","$muscle","$bodyfat") //db에 저장할 데이터

            //db에 저장
            databaseReference.child("Inbody").push().setValue(dataInput)


            //다이얼 로그 종료하기
            try{this.dismiss()}
            catch (e: Exception){
                Log.d("dissmiss errer","$e")}

        }

    }


}