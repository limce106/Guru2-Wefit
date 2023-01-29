package com.example.guru2.graph_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guru2.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.fragment_graph_view_pager.*


class GraphViewPager : Fragment() {

    private var weight : String? = null //몸무게 텍스트
    private var muscle : String? = null //골격근량 텍스트
    private var bodyfat : String? = null //체지방률 텍스트
    private var date : String? = null //기록 등록 날짜
    lateinit var lineChart: LineChart
    var dateList = mutableListOf<String>() //날짜 데이터 배열
    var weightList = mutableListOf<String>() //몸무게 데이터 배열
    var muscleList = mutableListOf<String>() //골격근량 데이터 배열


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weight = it.getString("weight","")
            muscle = it.getString("muscle","")
            bodyfat = it.getString("bodyfat","")
            date = it.getString("date","")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_graph_view_pager, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_weight.text = "체중\n"+ weight+"kg"
        btn_muscle.text = "골격근량\n"+muscle+"kg"
        btn_bodyfat.text = "체지뱡률\n"+bodyfat+"%"
        text_graph_date.text = date

        lineChart = view.findViewById(R.id.lineChart) as LineChart //꺾은 선 그래프

        dateList.add("22.10.02")
        dateList.add("22.11.02")
        weightList.add("51.3")
        weightList.add("50.3")
        muscleList.add("17.1")
        muscleList.add("19.1")

        val entries = ArrayList<Entry>()
        for(i in 0 until weightList.size){
            entries.add(Entry(i.toFloat(),weightList[i].toFloat()))
        }
        val labels = ArrayList<String>()
        for(i in 0 until dateList.size){
            labels.add(dateList[i])
        }
        val dateset = LineDataSet(entries,"")
        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        lineChart.getTransformer((YAxis.AxisDependency.LEFT))
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        val data = LineData(dateset)

        lineChart.data = data
        lineChart.invalidate()


    }

    companion object{
        fun newInstance(date:String, weight:String, muscle:String,bodyfat:String) =
            GraphViewPager().apply{
                arguments = Bundle().apply{
                    putString("date",date)
                    putString("weight",weight)
                    putString("muscle",muscle)
                    putString("bodyfat",bodyfat)
                }
            }

    }


}