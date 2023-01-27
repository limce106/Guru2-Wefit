package com.example.guru2.Records

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.guru2.R
import kotlinx.android.synthetic.main.exercise_name_form.view.*

class RecyclerAdapterExerName(private var ExerciseNames: ArrayList<ExerciseNameModel>):
    RecyclerView.Adapter<RecyclerAdapterExerName.ViewHolder>(), Filterable {

    override fun getItemCount(): Int = ExerciseNames.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        // 운동 항목 클릭 이벤트
        holder.itemView.setOnClickListener {
            nameClickListener.onClick(it, position)
        }
        holder.apply {
            itemView.tag = ExerciseNames[position]
        }
        holder.bind(ExerciseNames[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val inflatedView=LayoutInflater.from(parent.context).inflate(R.layout.exercise_name_form, parent, false)
        return ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: ExerciseNameModel) {
            view.tv_exerciseName.text = item.exerciseName
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
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d("Search2", "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본배열 복제
            val filterList : ArrayList<ExerciseNameModel> = ArrayList<ExerciseNameModel>()

            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = ExerciseNames
                results.count = ExerciseNames.size

                return results
            } else{
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