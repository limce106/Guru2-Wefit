package com.example.guru2.calender_user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.google.firebase.firestore.core.ActivityScope.bind


class RecyclerViewAdapter(val itemList: ArrayList<Schedule>):RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){


    //아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calender_item,parent,false)
        return RecyclerViewHolder(view)
    }

    //view에 내용 입력
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.hour.text = itemList[position].hour
        holder.minute.text = itemList[position].minute
    }

    //리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }


    //레이아웃 내 View 연결
    inner class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val hour = itemView.findViewById<TextView>(R.id.hour)
        val minute = itemView.findViewById<TextView>(R.id.minute)
    }



}