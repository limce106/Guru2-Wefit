package com.example.guru2.Recommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.example.guru2.Records.ExerciseRecModel
import kotlinx.android.synthetic.main.trainer_recommend_form.view.*

class RecyclerAdapterTrainerRecommend():
    RecyclerView.Adapter<RecyclerAdapterTrainerRecommend.ViewHolder>() {
    private var arrayList = ArrayList<ExerciseRecModel>()

    constructor(arrayList: ArrayList<ExerciseRecModel>, context: android.content.Context) : this() {
        this.arrayList = arrayList
        val ct: android.content.Context = context
    }

    @NonNull
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView=
            LayoutInflater.from(parent.context)
                .inflate(R.layout.trainer_recommend_form, parent, false)
        return ViewHolder(inflatedView)
    }

//    override fun onBindViewHolder(@NonNull holder: RecyclerAdapterTrainerRecommend.ViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            exerciseRecClickListener.onClick(it, position)
//        }
//        holder.apply {
//            itemView.tag = arrayList!![position]
//        }
//        holder.bind(arrayList!![position])
//    }


    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {

        holder.bind(arrayList!![position])

    }

    override fun getItemCount(): Int {
        // 삼항 연산자
        return if (arrayList != null) arrayList.size else 0
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: ExerciseRecModel) {
            view.tv_recommendExerName.text = item.exerName2
            view.tv_recommendSetCount.text = item.count + "회" + item.set + "세트"
        }
    }

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