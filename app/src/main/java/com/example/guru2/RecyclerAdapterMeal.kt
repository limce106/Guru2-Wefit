package com.example.guru2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_input_meal.view.*
import kotlinx.android.synthetic.main.meal_record_form.view.*

class RecyclerAdapterMeal(private var mealRecords: ArrayList<MealRecModel>):
    RecyclerView.Adapter<RecyclerAdapterMeal.ViewHolder>(){

    override fun getItemCount(): Int = mealRecords.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mealRecClickListener.onClick(it, position)
        }
        holder.apply {
            itemView.tag = mealRecords[position]
        }
        holder.bind(mealRecords[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerAdapterMeal.ViewHolder {
        val inflatedView=
            LayoutInflater.from(parent.context).inflate(R.layout.meal_record_form, parent, false)
        return RecyclerAdapterMeal.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item:MealRecModel) {
            view.mealImg.setImageDrawable(item.mealImg)
            view.tv_mealTime.text = item.timeSlot
            view.tv_eatTime.text = item.eatTime
            view.tv_menuName.text = item.mealName
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