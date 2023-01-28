package com.example.guru2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*
import java.io.StringBufferInputStream

class chatActivity : AppCompatActivity() {

    private lateinit var receiverName: String
    private lateinit var receiverUid: String

    lateinit var mAuth: FirebaseAuth//인증 객체
    lateinit var mDbRef:DatabaseReference//DB 객체

    private lateinit var receiverRoom:String //받는 대화방
    private lateinit var senderRoom:String //보낸 대화방

    lateinit var btn_send:Button

    private lateinit var messageList:ArrayList<Message>

    lateinit var chat_RecyclerView: RecyclerView

    lateinit var message_edit:EditText

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //초기화
        messageList=ArrayList()
        val messageAdapter: MessageAdapter= MessageAdapter(this, messageList)

        //RecyclerView
        chat_RecyclerView=findViewById(R.id.chat_recyclerView)

        chat_RecyclerView.layoutManager=LinearLayoutManager(this)
        chat_RecyclerView.adapter=messageAdapter

        //넘어온 데이터 변수에 담기
        receiverName=intent.getStringExtra("reg_name").toString()
        receiverUid=intent.getStringExtra("uId").toString()

        mAuth=FirebaseAuth.getInstance()
        mDbRef=FirebaseDatabase.getInstance().reference

        //접속자 Uid
        val senderUid=mAuth.currentUser?.uid
        //보낸이방
        senderRoom=receiverUid+senderUid
        //받는이방
        receiverRoom=senderUid+receiverUid

        //액션바에 상대방 이름 보여주기
        supportActionBar?.title=receiverName

        btn_send=findViewById(R.id.btn_send)
        message_edit=findViewById(R.id.message_edit)

        //메시지 전송 버튼
        //입력한 메시지는 DB에 저장이 되고 DB에 저장된 메시지를 화면에 보여줌
        btn_send.setOnClickListener {

            val message=message_edit.text.toString()
            val messageObject=Message(message,senderUid)

            //데이터 저장
            mDbRef.child("chats").child(senderRoom).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    //저장성공하면
                    mDbRef.child("chats").child(receiverRoom).child("messages").push()
                        .setValue(messageObject)}
        }

        //입력값 초기화
        message_edit.setText("")


    //메시지 가져오기
    mDbRef.child("chats").child(senderRoom).child("messages")
        .addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()

                for(postSnapshat in snapshot.children){
                    val message=postSnapshat.getValue(Message::class.java)
                    messageList.add(message!!)
                }
                //적용
                messageAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
  }
}


