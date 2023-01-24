package com.example.guru2

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment


class IndividualExerciseDialog : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //다이얼로그 배경 투명하게
        return inflater.inflate(R.layout.fragment_individual_exercise_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //객체 생성
        val btn_indi_add = view.findViewById<Button>(R.id.btn_indi_add)
        val timePicker = view.findViewById<TimePicker>(R.id.time_individual)
        var edit_hour :Int = 0
        var edit_minute:Int= 0

        timePicker.setOnTimeChangedListener{timePicker,hourOfday,minute ->

            var edit_hour :Int = hourOfday
            var edit_minute:Int=minute
        }


        btn_indi_add.setOnClickListener{


            //다이얼 로그 종료하기
            try{this.dismiss()}
            catch (e: Exception){
                Log.d("dissmiss errer","$e")}
        }

    }

    fun getInstance():IndividualExerciseDialog{
        return IndividualExerciseDialog()
    }



}