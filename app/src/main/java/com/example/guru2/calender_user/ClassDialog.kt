package com.example.guru2.calender_user

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import kotlinx.android.synthetic.main.fragment_class_dialog.*


class ClassDialog : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = inflater.inflate(R.layout.fragment_class_dialog, container, false)
        val recyclerviewclass=view.findViewById<RecyclerView>(R.id.recyclerviewclass) //리사이클러 뷰 객체
        val itemList = arrayListOf<ClassScheduleItem>() //아이템 배열
        val ListAdapter = RecyclerViewClassAdapter(itemList) //어댑터
        val dialog: ClassReservationDialog = ClassReservationDialog().getInstance() //예약 확정 팝업창


        recyclerviewclass.adapter= ListAdapter //어댑터 연결
        recyclerviewclass.layoutManager= LinearLayoutManager(activity)

        //아이템 추가
        itemList.add(ClassScheduleItem("12:00","13:00","2","10",))
        itemList.add(ClassScheduleItem("14:00","15:00","6","10"))
        itemList.add(ClassScheduleItem("18:00","19:00","4","10"))

        //리스트가 변경됨을 어댑터에 알림
        ListAdapter.notifyDataSetChanged()

        return view

    }


    fun getInstance(): ClassDialog {
        return ClassDialog()
    }

}