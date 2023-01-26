package com.example.guru2.Records

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import com.google.firebase.database.core.Context
import kotlinx.android.synthetic.main.meal_record_form.view.*


class RecyclerAdapterMeal2(arrayList: ArrayList<MealRecModel>?, context: android.content.Context?):
    RecyclerView.Adapter<RecyclerAdapterMeal2.ViewHolder>() {
    private var arrayList: ArrayList<MealRecModel>? = null
    private var context: Context? = null
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

//    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
//    //선택한 액티비티에 대한 context를 가져올 때 필요하다.
//    fun RecyclerAdapterMeal2(arrayList: ArrayList<MealRecModel>?, context: Context?) {
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
                .inflate(R.layout.meal_record_form, parent, false)
        return ViewHolder(inflatedView)
    }

//    override fun onBindViewHolder(@NonNull holder: RecyclerAdapterMeal2.ViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            mealRecClickListener.onClick(it, position)
//        }
//        holder.apply {
//            itemView.tag = arrayList!![position]
//        }
//        holder.bind(arrayList!![position])
//    }


    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView)
//            .load(arrayList!![position].mealImg)
//            .into(holder.mealImg)
//        holder.edtMealDate.text = arrayList!![position].eatDate
//        holder.edtMealName.text = arrayList!![position].mealName
//        holder.eatTime.text = arrayList!![position].eatTime
//        holder.edtTimeSlot.text = arrayList!![position].timeSlot

        holder.bind(arrayList!![position])

    }

    override fun getItemCount(): Int {
        // 삼항 연산자
        return if (arrayList != null) arrayList!!.size else 0
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: MealRecModel) {
            view.tv_eatDate.text = item.eatDate
            view.tv_mealTime.text = item.timeSlot
            view.tv_menuName.text = item.mealName
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
