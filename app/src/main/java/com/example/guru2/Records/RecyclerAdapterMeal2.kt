package com.example.guru2.Records

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import kotlinx.android.synthetic.main.meal_record_form.view.*


class RecyclerAdapterMeal2():
    RecyclerView.Adapter<RecyclerAdapterMeal2.ViewHolder>() {
    private var arrayList = ArrayList<MealRecModel>()

    constructor(arrayList: ArrayList<MealRecModel>, context: android.content.Context) : this() {
        this.arrayList = arrayList
        val ct: android.content.Context = context
    }

    @NonNull
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView=
            LayoutInflater.from(parent.context)
                .inflate(R.layout.meal_record_form, parent, false)
        val holder: ViewHolder = ViewHolder(inflatedView)
        return holder
    }


    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        // 삼항 연산자
        return if (arrayList != null) arrayList.size else 0
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view: View = v

        fun bind(item: MealRecModel) {
            view.tv_eatDate.text = item.eatDate + "  " + item.eatTime
            view.tv_timeSlot.text = item.timeSlot
            view.tv_menuName.text = item.mealName + "  " + item.eatAmount
        }
    }

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
