package com.example.guru2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.aboutUser.User
import com.example.guru2.aboutUser.UserAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [userList.newInstance] factory method to
 * create an instance of this fragment.
 */
class userList : Fragment() {
    // TODO: Rename and change types of parameters


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        lateinit var adapter: UserAdapter
        lateinit var mAuth: FirebaseAuth
        lateinit var mDbRef: DatabaseReference
        lateinit var list:ArrayList<User>
        lateinit var user_recyclerView: RecyclerView


        val view = inflater.inflate(R.layout.fragment_class_dialog, container, false)



        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference
        list = ArrayList()
        adapter = UserAdapter(this, list)

        user_recyclerView.layoutManager = LinearLayoutManager(activity)
        user_recyclerView.adapter = adapter
        return inflater.inflate(R.layout.fragment_user_list, container, false)

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment userList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            userList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}