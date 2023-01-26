package com.example.guru2.Records

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.google.firebase.database.core.Context
import kotlinx.android.synthetic.main.exercise_record_form.view.*

class RecyclerAdapterExercise2(arrayList: ArrayList<ExerciseRecModel>?, context: android.content.Context?):
    RecyclerView.Adapter<RecyclerAdapterExercise2.ViewHolder>() {
    private var arrayList: ArrayList<ExerciseRecModel>? = null
    private var context: Context? = null
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

//    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
//    //선택한 액티비티에 대한 context를 가져올 때 필요하다.
//    fun RecyclerAdapterExercise2(arrayList: ArrayList<ExerciseRecModel>?, context: Context?) {
//        this.arrayList = arrayList
//        this.context = context
//    }

    @NonNull
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.arrayList = arrayList
        this.context = context
        val inflatedView=
            LayoutInflater.from(parent.context)
                .inflate(R.layout.exercise_record_form, parent, false)
        return ViewHolder(inflatedView)
    }

//    override fun onBindViewHolder(@NonNull holder: RecyclerAdapterExercise2.ViewHolder, position: Int) {
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
        return if (arrayList != null) arrayList!!.size else 0
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: ExerciseRecModel) {
            view.tv_exerciseDate.text = item.exerDate
            view.tv_exerciseName2.text = item.exerName2
            view.tv_countSet.text = item.count + "회" + item.set + "세트"
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