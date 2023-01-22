package com.example.guru2

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker.OnTimeChangedListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input_meal.*
import kotlinx.android.synthetic.main.fragment_input_meal.view.*

class InputMealFragment : Fragment() {
    private var uri:String?=null
    var isSaveDta: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_input_meal, container, false)
        val bundle = Bundle()
        bundle.putString("timeSlot", rootView.findViewById<EditText>(R.id.edtTimeSlot).text.toString())
        bundle.putString("mealName", rootView.findViewById<TextView>(R.id.edtMealName).text.toString())
        bundle.putBoolean("isSaveData", isSaveDta)

        // 버튼 클릭 시 권한 승인 요청
        rootView.btnLoadImg.setOnClickListener(){
            requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        rootView.btnSaveMealrecord.setOnClickListener() {
            isSaveDta = true
        }
        return rootView
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
            if(isGranted){
                Toast.makeText(context, "권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
//                val intent = Intent()
//                intent.type="image/*"
//                intent.action = Intent.ACTION_GET_CONTENT
//                startActivity(intent)
                val i = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(i, 1);
            }
            else{
                Toast.makeText(context, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TimePicker 클릭 이벤트: 먹은 시간 텍스트 변경
        tp_Choice_eatTime.setOnTimeChangedListener(OnTimeChangedListener { timePicker, hour, minute -> // 오전 / 오후 를 확인하기 위한 if 문
            var hour = hour
            if (hour > 12) {
                hour -= 12
                tv_eatTime.setText("오후 " + hour + "시 " + minute + "분 선택")
            } else {
                tv_eatTime.setText("오전 " + hour + "시 " + minute + "분 선택")
            }
        })

//        // 시간대 목록
//        spinnerMealTime.adapter=ArrayAdapter.createFromResource(requireContext(), R.array.list_mealtime,
//            android.R.layout.simple_spinner_item)
//
//        // 시간대 선택 리스너
//        val mealTime: String
//        spinnerMealTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            // 시간대를 선택했을 때
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                when (position) {
//                    // 아침
//                    0 -> {
//                        mealTime="아침"
//                    }
//                    // 점심
//                    1 -> {
////                        title_tv.setText(spinnerMealTime.selectedItem.toString())
////                        name_tv.setText("정수아")
////                        content_tv.setText("시 내용 입력 (생략)")
//                    }
//                    // 저녁
//                    2 -> {
//
//                    }
//                    // 간식
//                    3 -> {
//
//                    }
//                    //일치하는게 없는 경우
//                    else -> {
//
//                    }
//                }
//            }
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputMealFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}