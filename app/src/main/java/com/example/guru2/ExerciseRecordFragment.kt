package com.example.guru2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_exercise_record.*


class ExerciseRecordFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_exercise_record, container, false)

        val fab_add: FloatingActionButton = rootView.findViewById(R.id.fab_add)
        val mActivity = containerActivity.getInstance()
        fab_add.setOnClickListener() {
            mActivity?.setFrag(0)
            Log.d("test", "화면 전환")
        }

        return rootView
    }




}