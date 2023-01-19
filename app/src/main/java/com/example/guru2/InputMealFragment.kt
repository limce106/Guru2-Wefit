package com.example.guru2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TimePicker.OnTimeChangedListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input_meal.*

private const val ARG_URI="uri"

class InputMealFragment : Fragment() {
    private var uri:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uri=it.getString(ARG_URI)
        }
    }

    // 앨범 권한 처리
    val PERMISSION_Album = 101

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted->
            if(isGranted){
                Toast.makeText(context, "Grant", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Deny", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 버튼 클릭 시 권한 승인 요청
        btnLoadImg.setOnClickListener(){
            //requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_Album)
            requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        return inflater.inflate(R.layout.fragment_input_meal, container, false)
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
//            permissionGranted(requestCode)
//        } else {
//            permissionDenied(requestCode)
//        }
//    }
//
//    // 권한 승인 시
//    private fun permissionGranted(requestCode: Int) {
//        when (requestCode) {
//            //PERMISSION_CAMERA -> openCamera()
//            PERMISSION_Album -> openGallery()
//        }
//    }
//
//    // 권한 거부 시
//    private fun permissionDenied(requestCode: Int) {
//        when (requestCode) {
////            PERMISSION_CAMERA -> Toast.makeText(
////                this,
////                "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",
////                Toast.LENGTH_LONG
////            ).show()
//
//            PERMISSION_Album -> Toast.makeText(
//                this,
//                "저장소 권한을 승인해야 앨범에서 이미지를 불러올 수 있습니다.",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//
//    fun openGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = MediaStore.Images.Media.CONTENT_TYPE
//        startActivityForResult(intent, REQUEST_STORAGE)
//
//    }
//
//    //  앨범 선택 이미지 uri 값을 받아 이미지뷰에 띄워주기
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == RESULT_OK) {
//            when (requestCode) {
//                REQUEST_CAMERA -> {
//                    realUri?.let { uri ->
//                        mealImg.setImageURI(uri)
//                    }
//                }
//                REQUEST_STORAGE -> {
//                    data?.data?.let { uri ->
//                        mealImg.setImageURI(uri)
//                    }
//                }
//            }
//        }
//    }

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

        // 시간대 목록
        spinnerMealTime.adapter=ArrayAdapter.createFromResource(requireContext(), R.array.list_mealtime,
            android.R.layout.simple_spinner_item)

        // 시간대 선택 리스너
        spinnerMealTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            // 시간대를 선택했을 때
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    // 아침
                    0 -> {

                    }
                    // 점심
                    1 -> {
//                        title_tv.setText(spinnerMealTime.selectedItem.toString())
//                        name_tv.setText("정수아")
//                        content_tv.setText("시 내용 입력 (생략)")
                    }
                    // 저녁
                    2 -> {

                    }
                    // 간식
                    3 -> {

                    }
                    //일치하는게 없는 경우
                    else -> {

                    }
                }
            }
        }
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