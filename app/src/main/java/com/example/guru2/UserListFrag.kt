package com.example.guru2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class UserListFrag : Fragment() {

    var list:ArrayList<User> = ArrayList()
    var mAuth: FirebaseAuth = Firebase.auth
    var mDbRef: DatabaseReference = Firebase.database.reference
    var adapter: UserAdapter = UserAdapter(requireActivity(), list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        val userRecyclerView: RecyclerView = view.findViewById(R.id.user_recyclerView)
        val user_searchView: SearchView = view.findViewById(R.id.user_searchView)



        userRecyclerView.layoutManager = LinearLayoutManager(activity)
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

        return view
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
                Toast.makeText(activity, "No Data found", Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filteredList)
            }
        }


        //사용자 정보 가져오기
        mDbRef.child("user").addValueEventListener(object: ValueEventListener {

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