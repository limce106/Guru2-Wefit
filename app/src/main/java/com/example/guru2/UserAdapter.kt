package com.example.guru2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class UserAdapter(private val context: UserListFrag, private val userList:ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(view)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        //데이터 설정
        val currentUser=userList[position]
        holder.nameText.text=currentUser.reg_name
        val userChat : Chat = Chat()

        val bundle = Bundle()
        holder.itemView.setOnClickListener{

            bundle.putString("name", currentUser.reg_name)
            bundle.putString("uid", currentUser.uId)

            userChat.arguments = bundle

            context.activity?.supportFragmentManager?.let { fragmentManager -> userChat}

        }
    }
    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameText: TextView =itemView.findViewById(R.id.name_text)
    }



    fun findUID(id: String, position: Int){
        val database = FirebaseDatabase.getInstance()
        val databaseReference = database.getReference("user")

//        databaseReference.orderByChild("reg_id").equalTo(id).on("value", function(snapshot) {
//            console.log(snapshot.val());
//            snapshot.forEach(function(data) {
//                console.log(data.key);
//            });
//        });

        databaseReference.orderByChild("reg_id").equalTo(id)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
}