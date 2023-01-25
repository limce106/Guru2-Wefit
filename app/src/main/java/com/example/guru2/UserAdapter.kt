package com.example.guru2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (val context: Context, var userList:ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameText: TextView =itemView.findViewById(R.id.chat_textview_title)
    }

    fun setFilteredList(userList:ArrayList<User>){
        this.userList=userList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.activity_user_list, parent, false)
        return UserViewHolder(view)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userList[position]
        holder.nameText.text=currentUser.reg_name

        holder.itemView.setOnClickListener{
            val intent = Intent(context, chatActivity::class.java)
            intent.putExtra("name", currentUser.reg_name)
            intent.putExtra("name", currentUser.uId)

            context.startActivity(intent)
    }
}}