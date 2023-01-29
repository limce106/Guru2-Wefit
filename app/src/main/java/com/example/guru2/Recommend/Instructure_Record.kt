package com.example.guru2.Recommend

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.guru2.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Instructure_Record : Fragment() {
    lateinit var strNickname: String
    var isTrainerExist: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_instructure__record, container, false)

        // 닉네임 입력 변화 확인
        var edtNickName = rootView.findViewById<EditText>(R.id.edtNickName)
        checkChanges(edtNickName)

        val database = FirebaseDatabase.getInstance()
        val databaseReference = database.getReference("user") // DB 테이블 연결
        val btnOk = rootView.findViewById<Button>(R.id.btnIDOk)
        btnOk.setOnClickListener() {
            databaseReference.orderByChild("reg_id").equalTo(strNickname)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if(!::strNickname.isInitialized || strNickname == "") {
                            Toast.makeText(context, "닉네임을 입력하세요.", Toast.LENGTH_SHORT).show()
                        }else{
                            if(dataSnapshot.exists()){
                                Toast.makeText(context, "확인되었습니다.", Toast.LENGTH_SHORT).show()
                                isTrainerExist = true
                            } else {
                                Toast.makeText(context, "존재하지 않는 회원입니다." +
                                        " 다시 입력하세요.", Toast.LENGTH_SHORT).show()
                                isTrainerExist = false
                            }
                        }
                    }

                    override fun onCancelled(@NonNull databaseError: DatabaseError) {
                        Log.e("UserID", databaseError.toException().toString()) // 에러문 출력
                    }
                })
        }

        return rootView
    }

    fun checkChanges(edtNickname: EditText) {

        edtNickname.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                strNickname = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int, ) {}

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Instructure_Record().apply {
                arguments = Bundle().apply {

                }
            }
    }
}