package com.example.guru2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_name_form.view.*

class RecyclerAdapterExerName(private var ExerciseNames: ArrayList<ExerciseNameModel>):
    RecyclerView.Adapter<RecyclerAdapterExerName.ViewHolder>(), Filterable {

    override fun getItemCount(): Int = ExerciseNames.size

    override fun onBindViewHolder(holder: RecyclerAdapterExerName.ViewHolder, position: Int){
        // 운동 항목 클릭 이벤트
        holder.itemView.setOnClickListener {
            nameClickListener.onClick(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerAdapterExerName.ViewHolder {
        val inflatedView=LayoutInflater.from(parent.context).inflate(R.layout.exercise_name_form, parent, false)
        return RecyclerAdapterExerName.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item:ExerciseNameModel) {
            view.tv_exerciseName.text = item.exerciseName
            view.setOnClickListener(listener)
        }
    }

    // 검색 기능
    var filteredExernames = ArrayList<ExerciseNameModel>()
    var exernameFilter = ExerNameFilter()

    init {
        filteredExernames.addAll(ExerciseNames)
    }

    override fun getFilter(): Filter {
        return exernameFilter
    }

    inner class ExerNameFilter : Filter() {
        // 입력받은 문자열에 대한 처리
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterString = constraint.toString()
            val results = FilterResults()

            //검색이 필요없을 경우를 위해 원본배열 복제
            val filterList : ArrayList<ExerciseNameModel> = ArrayList<ExerciseNameModel>()

            //공벡제외 아무런 값도 입력하지 않았을 경우 ->원본배열
            if (filterString.trim { it <= ' '}.isEmpty()) {
                //필터링 작업으로 계산된 모든 값
                results.values = ExerciseNames
                //필터링 작업으로 계산된 값의 수
                results.count = ExerciseNames.size
                return results

                //20글자 수 이하일 때
            } else if (filterString.trim {it <= ' '}.length <= 20) {
                for (searchExerName in ExerciseNames) {
                    if (searchExerName.exerciseName.contains(filterString)) {
                        filterList.add(searchExerName)
                    }
                }
            }
            results.values = filterList
            results.count = filterList.size

            return results
        }

        //처리에 대한 결과물
        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            filteredExernames.clear()
            filteredExernames.addAll(results.values as ArrayList<ExerciseNameModel>)
            notifyDataSetChanged()
        }
    }

    // 리사이클러뷰 클릭 이벤트
    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.nameClickListener = onItemClickListener
    }
    // setItemClickListener로 설정한 함수 실행
    private lateinit var nameClickListener : OnItemClickListener
}