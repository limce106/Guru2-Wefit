package com.example.guru2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.guieffect.qual.UI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [myPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class myPage : Fragment() {

    var list:ArrayList<User> = ArrayList()
    var mAuth = FirebaseAuth.getInstance()
    var mDbRef:DatabaseReference = FirebaseDatabase.getInstance().reference
    var user=FirebaseAuth.getInstance().currentUser
    var userId= user?.uid
    var adapter: UserAdapter = UserAdapter(requireActivity(), list)



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
        val view= inflater.inflate(R.layout.fragment_my_page, container, false)



        var page_name : TextView = view.findViewById(R.id.page_name)
        val page_id : TextView = view.findViewById(R.id.page_id)
        val page_gender : TextView = view.findViewById(R.id.page_gender)
        val page_purpose : TextView = view.findViewById(R.id.page_purpose)

        mDbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val userInfo=dataSnapshot.getValue(User::class.java)
                val key=dataSnapshot.key

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        // [END read_message]



//        mDbRef.child("user")
//            .addValueEventListener(object: ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//
//
//                      //  page_name=mAuth.getValue(User::class.java)
//
//
////                }
////
////                override fun onCancelled(error: DatabaseError) {
////                }
//
//            })



        // Inflate the layout for this fragment

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment myPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            myPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}