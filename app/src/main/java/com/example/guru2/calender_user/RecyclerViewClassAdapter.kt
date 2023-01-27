package com.example.guru2.calender_user

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import kotlinx.android.synthetic.main.calender_class_item.view.*

class RecyclerViewClassAdapter(val itemList: ArrayList<ClassScheduleItem>):RecyclerView.Adapter<RecyclerViewClassAdapter.RecyclerViewHolder>() {



    //아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calender_class_item,parent,false)

        return RecyclerViewHolder(view)
    }

    //view에 내용 입력
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.class_start_time.text = itemList[position].StartTime
        holder.class_end_time.text = itemList[position].EndTime
        holder.join_size.text = itemList[position].JoinSize
        holder.join_max_size.text = itemList[position].JoinMaxSize
        holder.itemView.btn_join.setOnClickListener{

        }
    }

    fun getstate(){
        return
    }

    //리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }


    //레이아웃 내 View 연결
    inner class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val class_start_time = itemView.findViewById<TextView>(R.id.class_start_time)
        val class_end_time = itemView.findViewById<TextView>(R.id.class_end_time)
        val join_size = itemView.findViewById<TextView>(R.id.join_size)
        val join_max_size = itemView.findViewById<TextView>(R.id.join_max_size)
    }

}