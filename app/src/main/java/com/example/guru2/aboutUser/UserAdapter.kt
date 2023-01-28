package com.example.guru2.aboutUser

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.example.guru2.chatActivity
import com.example.guru2.userList

class UserAdapter(private val context : Context, private var userList:ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)

    }

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameText: TextView =itemView.findViewById(R.id.name_text)
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