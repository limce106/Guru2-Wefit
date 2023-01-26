package com.example.guru2.calender_user

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.guru2.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class IndividualExerciseDialog : DialogFragment() {


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

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //다이얼로그 배경 투명하게
        return inflater.inflate(R.layout.fragment_individual_exercise_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //객체 생성
        val btn_indi_add = view.findViewById<Button>(R.id.btn_indi_add) //개인 운동 일정 추가하기 버튼
        val timePicker = view.findViewById<TimePicker>(R.id.time_individual)
        var edit_hour :Int = 0 //개인 운동 일정 시간
        var edit_minute:Int= 0 //개인 운동 일정 분

        timePicker.setOnTimeChangedListener{ timePicker, hourOfDay, minute ->

            edit_hour  = hourOfDay //시간 불러오기
            edit_minute =minute //분 불러오기

        }


        //개인 운동 일정 추가하기 버튼 클릭시
        btn_indi_add.setOnClickListener{

            val hour:Int = edit_hour
            val minute:Int=edit_minute

            //db에 저장
            databaseReference.child("individual-schedule").child("hour").push().setValue("$hour")
            databaseReference.child("individual-schedule").child("minute").push().setValue("$minute")

            //다이얼 로그 종료하기
            try{this.dismiss()}
            catch (e: Exception){
                Log.d("dissmiss errer","$e")}
        }

    }

    fun getInstance(): IndividualExerciseDialog {
        return IndividualExerciseDialog()
    }



}