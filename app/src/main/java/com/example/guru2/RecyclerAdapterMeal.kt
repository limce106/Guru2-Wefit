package com.example.guru2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterMeal(private var mealRec: ArrayList<MealRecModel>):
    RecyclerView.Adapter<RecyclerAdapterMeal.ViewHolder>(){

        override fun getItemCount(): Int = mealRec.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                RecyclerAdapterMeal.ViewHolder {
            val inflatedView=
                LayoutInflater.from(parent.context).inflate(R.layout.meal_record_form, parent, false)
            return RecyclerAdapterMeal.ViewHolder(inflatedView)
        }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item:MealRecModel) {
            //view.mealImg.setImageDrawable(item.mealImg)
            var edtTimeSlot: String = view.findViewById<EditText>(R.id.edtTimeSlot).text.toString()
            edtTimeSlot = item.timeSlot
            //var tv_eatTime: String = view.findViewById<EditText>(R.id.tv_eatTime).text.toString()
            //tv_eatTime = item.eatTime
            var edtMealName: String = view.findViewById<EditText>(R.id.edtMealName).text.toString()
            edtMealName = item.mealName
            view.setOnClickListener(listener)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mealRecClickListener.onClick(it, position)
        }
    }

    // 리사이클러뷰 클릭 이벤트
    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mealRecClickListener = onItemClickListener
    }
    // setItemClickListener로 설정한 함수 실행
    private lateinit var mealRecClickListener : OnItemClickListener
}