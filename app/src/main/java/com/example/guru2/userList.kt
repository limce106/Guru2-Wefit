package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class userList : AppCompatActivity() {
    lateinit var adapter: UserAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef:DatabaseReference
    private lateinit var list:ArrayList<User>
    lateinit var userRecyclerView: RecyclerView
    lateinit var user_searchView:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userRecyclerView = findViewById(R.id.user_recyclerView)
        user_searchView = findViewById(R.id.user_searchView)

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference
        list = ArrayList()
        adapter = UserAdapter(this, list)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter
        user_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }


    private fun filterList(query:String?){
        if(query!=null){
            val filteredList=ArrayList<User>()
            for(i in list){
                if(i.reg_name.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filteredList)
            }
        }


        //사용자 정보 가져오기
        mDbRef.child("user").addValueEventListener(object:ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                for(postSnapshot in snapshot.children){
                    //유저 정보
                    val currentUser=postSnapshot.getValue(User::class.java)
                    //내 아이디와 사용자 정보가 다를 때만
                    if(mAuth.currentUser?.uid!=currentUser?.uId){
                        list.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
                //데이터가 변경되면 함수가 실행됨
            }


            override fun onCancelled(error: DatabaseError) {
                //데이터가 캔슬됐을 때 실행됨
            }

        })









    }
}