package com.example.guru2

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil.setContentView
import com.example.guru2.databinding.ActivityMainBinding

class InputMealFragment : Fragment() {
    lateinit var binding : ActivityMainBinding
    var img_load=findview

    private val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        result.forEach {
            if(!it.value) {
                Toast.makeText(applicationContext, "권한 동의 필요!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        img_load.load(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            val itemList = listOf("아침", "점심", "저녁", "간식")
            val adapter = ArrayAdapter(this, R.layout.fragment_input_meal, itemList)
            binding.spinner.adapter = adapter

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input_meal, container, false)
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