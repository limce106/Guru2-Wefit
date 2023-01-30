package com.example.guru2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserAdapter(private val context: UserListFrag, private val userList:ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameText: TextView =itemView.findViewById(R.id.chat_textview_title)
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.fragment_user_list, parent, false)
        return UserViewHolder(view)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser=userList[position]
        holder.nameText.text=currentUser.reg_name

        val bundle = Bundle()
        holder.itemView.setOnClickListener{


            bundle.putString("name",currentUser.reg_name)
            bundle.putString("name",currentUser.uId)

//            intent.putExtra("name", currentUser.reg_name)
//            intent.putExtra("name", currentUser.uId)

   //         context.startActivity(intent)
    }
}}