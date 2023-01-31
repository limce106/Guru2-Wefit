package com.example.guru2.calender_user

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.example.guru2.Records.RecyclerAdapterMeal2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RecyclerViewAdapter():RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){
    private lateinit var itemList: ArrayList<Schedule>
    lateinit var ct: Context
    private lateinit var uidList: ArrayList<String>

    constructor(itemList: ArrayList<Schedule>, context: android.content.Context, uidList: ArrayList<String>) : this() {
        this.itemList = itemList
        ct = context
        this.uidList = uidList
    }


    //아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calender_item,parent,false)
        val holder: RecyclerViewAdapter.RecyclerViewHolder = RecyclerViewAdapter.RecyclerViewHolder(view)
        return holder
    }

    //view에 내용 입력
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(itemList[position])

        holder.iv_delete2.setOnClickListener(){
            removeData(position)
            Log.e("삭제", position.toString())
        }
    }

    //리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }


    //레이아웃 내 View 연결
    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val iv_delete2: ImageView = itemView.findViewById(R.id.iv_delete2)

        fun bind(item: Schedule)
        {
            val time = itemView.findViewById<TextView>(R.id.time)
            val type = itemView.findViewById<TextView>(R.id.schedule_type)
            val date = itemView.findViewById<TextView>(R.id.calender_date)
        }
    }

    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.scheduleClickListener = onItemClickListener
    }
    // setItemClickListener로 설정한 함수 실행
    private lateinit var scheduleClickListener : OnItemClickListener

    //데이터 삭제 함수
    fun removeData(position: Int){
        val database = FirebaseDatabase.getInstance()
        var user= FirebaseAuth.getInstance().currentUser
        var userId= user?.uid
        val myRef = database.getReference("schedule").child(userId!!)
        val builder = AlertDialog.Builder(ct)
        builder.setTitle("삭제")
            .setMessage("해당 항목을 삭제하시겠습니까?")
            .setPositiveButton("확인",
                DialogInterface.OnClickListener{ dialog, id ->
                    myRef.child(uidList[position]).removeValue().addOnSuccessListener {
                        Toast.makeText(ct, "삭제 완료", Toast.LENGTH_SHORT).show() }
                    itemList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemList.size)
                })
            .setNegativeButton("취소",
                DialogInterface.OnClickListener{ dialog, id ->
                    dialog.cancel()
                })

        // 다이얼로그 띄우기
        builder.show()
    }
}