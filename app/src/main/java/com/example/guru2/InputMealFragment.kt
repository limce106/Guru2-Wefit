package com.example.guru2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker.OnTimeChangedListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_input_meal.*
import kotlinx.android.synthetic.main.fragment_input_meal.view.*
import kotlinx.android.synthetic.main.meal_record_form.*


class InputMealFragment : Fragment() {
    private var uri:String?=null
    val mActivity = MainActivity.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_input_meal, container, false)

        // 사진 불러오기 클릭 시 권한 승인 요청
        rootView.btnLoadImg.setOnClickListener(){
            requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        // 저장하기 클릭 시 입력한 데이터 저장
        rootView.btnSaveMealrecord.setOnClickListener() {

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference()

            val dataInput = MealRecModel(
                mealImg.toString(), edtMealDate.text.toString(), edtTimeSlot.text.toString(),
                tv_mealTime.text.toString(), edtMealName.text.toString()
            )

            myRef.child("Swuni").push().setValue(dataInput)

        }

        return rootView
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
            // 갤러지 이미지 권한이 허가되었다면
            if(isGranted){
                Toast.makeText(context, "권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_PICK)
                intent.type="image/*"
                //intent.action = Intent.ACTION_GET_CONTENT
                startActivity(intent)
                mActivity?.activityResult
            }
            // 갤러지 이미지 권한이 거부되었다면
            else{
                Toast.makeText(context, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tp_Choice_eatTime.setIs24HourView(true);
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