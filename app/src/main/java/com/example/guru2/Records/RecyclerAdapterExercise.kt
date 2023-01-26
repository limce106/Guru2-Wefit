package com.example.guru2.Records

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import kotlinx.android.synthetic.main.exercise_record_form.view.*

class RecyclerAdapterExercise(private var exerciseRec: ArrayList<ExerciseRecModel>):
    RecyclerView.Adapter<RecyclerAdapterExercise.ViewHolder>(){
        override fun getItemCount(): Int = exerciseRec.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                exerciseRecClickListener.onClick(it, position)
            }
            holder.bind(exerciseRec[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                ViewHolder {
            val inflatedView=
                LayoutInflater.from(parent.context).inflate(R.layout.exercise_record_form, parent, false)
            return ViewHolder(inflatedView)
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            private var view: View = v
            fun bind(item: ExerciseRecModel) {
                view.tv_exerciseName2.text = item.exerName2
                view.tv_countSet.text = item.count + "회" + item.set + "세트"
            }
        }

        // 리사이클러뷰 클릭 이벤트
        // 리스너 인터페이스
        interface OnItemClickListener {
            fun onClick(v: View, position: Int)
        }
        // 외부에서 클릭 시 이벤트 설정
        fun setItemClickListener(onItemClickListener: OnItemClickListener) {
            this.exerciseRecClickListener = onItemClickListener
        }
        // setItemClickListener로 설정한 함수 실행
        private lateinit var exerciseRecClickListener : OnItemClickListener
}