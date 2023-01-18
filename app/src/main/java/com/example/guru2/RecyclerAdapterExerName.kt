package com.example.guru2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_list.view.*

class RecyclerAdapterExerName(private val ExerciseNames: ArrayList<ExerciseListModel>):
    RecyclerView.Adapter<RecyclerAdapterExerName.ViewHolder>() {

    override fun getItemCount(): Int = ExerciseNames.size

    override fun onBindViewHolder(holder: RecyclerAdapterExerName.ViewHolder, position: Int){
        val item = ExerciseNames[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: "+item.exerciseName, Toast.LENGTH_SHORT).show()
        }
        holder.apply{
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerAdapterExerName.ViewHolder {
        val inflatedView=LayoutInflater.from(parent.context).inflate(R.layout.exercise_list, parent, false)
        return RecyclerAdapterExerName.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item:ExerciseListModel) {
            view.tv_exerciseName.text = item.exerciseName
            view.setOnClickListener(listener)
        }
    }
}