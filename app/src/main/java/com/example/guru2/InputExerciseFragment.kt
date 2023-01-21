package com.example.guru2

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.popup_customexercise.*
import kotlinx.android.synthetic.main.popup_exercisecount.*

class InputExerciseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_input_exercise, container, false)
        val list = ArrayList<ExerciseNameModel>()

        //가슴
        list.add(ExerciseNameModel("니 푸시업"))
        list.add(ExerciseNameModel("중량 푸시업"))
        list.add(ExerciseNameModel("중량 딥스"))
        list.add(ExerciseNameModel("인클라인 벤치프레스 머신"))
        list.add(ExerciseNameModel("덤벨 풀오버"))
        list.add(ExerciseNameModel("푸시업"))
        list.add(ExerciseNameModel("펙덱 플라이 머신"))
        list.add(ExerciseNameModel("인클라인 덤벨 플라이"))
        list.add(ExerciseNameModel("케이블 크로스 오버"))
        list.add(ExerciseNameModel("체스트 프레스 머신"))
        list.add(ExerciseNameModel("덤벨 플라이"))
        list.add(ExerciseNameModel("딥스"))
        list.add(ExerciseNameModel("인클라인 벤치프레스"))
        list.add(ExerciseNameModel("인클라인 덤벨 프레스"))
        list.add(ExerciseNameModel("덤벨 프레스"))
        list.add(ExerciseNameModel("푸쉬업"))
        list.add(ExerciseNameModel("벤치프레스"))

        // 등
        list.add(ExerciseNameModel("어시스트 풀업 머신"))
        list.add(ExerciseNameModel("중량 하이퍼 익스텐션"))
        list.add(ExerciseNameModel("백 익스텐션"))
        list.add(ExerciseNameModel("중량 풀업"))
        list.add(ExerciseNameModel("인버티드 로우"))
        list.add(ExerciseNameModel("바벨 풀오버"))
        list.add(ExerciseNameModel("원암 덤벨 로우"))
        list.add(ExerciseNameModel("시티드 케이블 로우"))
        list.add(ExerciseNameModel("친 업"))
        list.add(ExerciseNameModel("백 익스텐션 머신"))
        list.add(ExerciseNameModel("굿모닝 엑서사이즈"))
        list.add(ExerciseNameModel("펜들레이 로우"))
        list.add(ExerciseNameModel("시티드 로우 머신"))
        list.add(ExerciseNameModel("랫 풀 다운"))
        list.add(ExerciseNameModel("바벨 로우"))
        list.add(ExerciseNameModel("덤벨 로우"))
        list.add(ExerciseNameModel("풀업"))

        // 어깨
        list.add(ExerciseNameModel("덤벨 업라이트 로우"))
        list.add(ExerciseNameModel("이지바 업라이트 로우"))
        list.add(ExerciseNameModel("아놀드 덤벨 프레스"))
        list.add(ExerciseNameModel("숄더 프레스 머신"))
        list.add(ExerciseNameModel("케이블 리버스 플라이"))
        list.add(ExerciseNameModel("벤트오버 덤벨 레터럴 레이즈"))
        list.add(ExerciseNameModel("바벨 업라이트 로우"))
        list.add(ExerciseNameModel("페이스 풀"))
        list.add(ExerciseNameModel("비하인드 넥 프레스"))
        list.add(ExerciseNameModel("덤벨 슈러그"))
        list.add(ExerciseNameModel("덤벨 프론트 레이즈"))
        list.add(ExerciseNameModel("덤벨 숄더 프레스"))
        list.add(ExerciseNameModel("덤벨 레터럴 레이즈"))
        list.add(ExerciseNameModel("오버헤드 프레스"))

        // 팔
        list.add(ExerciseNameModel("스컬 크러셔"))
        list.add(ExerciseNameModel("바벨 리스트 컬"))
        list.add(ExerciseNameModel("시티드 덤벨 익스텐션"))
        list.add(ExerciseNameModel("케이블 삼두 익스텐션"))
        list.add(ExerciseNameModel("이지바 컬"))
        list.add(ExerciseNameModel("케이블 푸시 다운"))
        list.add(ExerciseNameModel("덤벨 해머 컬"))
        list.add(ExerciseNameModel("덤벨 킥백"))
        list.add(ExerciseNameModel("덤벨 리스트 컬"))
        list.add(ExerciseNameModel("덤벨 삼두 익스텐션"))
        list.add(ExerciseNameModel("바벨 컬"))
        list.add(ExerciseNameModel("덤벨 컬"))

        // 하체
        list.add(ExerciseNameModel("덩키 킥"))
        list.add(ExerciseNameModel("아웃 싸이 머신"))
        list.add(ExerciseNameModel("힙 쓰러스트"))
        list.add(ExerciseNameModel("중량 힙 쓰러스트"))
        list.add(ExerciseNameModel("덤벨 스플릿 스쿼트"))
        list.add(ExerciseNameModel("중량 스텝업"))
        list.add(ExerciseNameModel("고블릿 스쿼트"))
        list.add(ExerciseNameModel("스텝업"))
        list.add(ExerciseNameModel("저처 스쿼트"))
        list.add(ExerciseNameModel("바벨 스플릿 스쿼트"))
        list.add(ExerciseNameModel("이너 싸이 머신"))
        list.add(ExerciseNameModel("에어 스쿼트"))
        list.add(ExerciseNameModel("루마니안 데드리프트"))
        list.add(ExerciseNameModel("런지"))
        list.add(ExerciseNameModel("스탠딩 카프 레이즈"))
        list.add(ExerciseNameModel("스모 데드리프트"))
        list.add(ExerciseNameModel("덤벨 런지"))
        list.add(ExerciseNameModel("레그 프레스"))
        list.add(ExerciseNameModel("레그 컬"))
        list.add(ExerciseNameModel("레그 익스텐션"))
        list.add(ExerciseNameModel("프론트 스쿼트"))
        list.add(ExerciseNameModel("컨벤셔널 데드리프트"))
        list.add(ExerciseNameModel("바벨 백스쿼트"))

        //코어
        list.add(ExerciseNameModel("버드 독"))
        list.add(ExerciseNameModel("벤치 디클라인 크런치"))
        list.add(ExerciseNameModel("슈퍼맨"))
        list.add(ExerciseNameModel("니 업"))
        list.add(ExerciseNameModel("핸즈 워킹"))
        list.add(ExerciseNameModel("스탠딩 사이드 크런치"))
        list.add(ExerciseNameModel("데드 버그"))
        list.add(ExerciseNameModel("롤 업"))
        list.add(ExerciseNameModel("토즈 투 바"))
        list.add(ExerciseNameModel("힐 터치"))
        list.add(ExerciseNameModel("할로우 포지션"))
        list.add(ExerciseNameModel("할로우 락"))
        list.add(ExerciseNameModel("행잉 레그 레이즈"))
        list.add(ExerciseNameModel("크런치"))
        list.add(ExerciseNameModel("브이 업"))
        list.add(ExerciseNameModel("바이시클 크런치"))
        list.add(ExerciseNameModel("롤 아웃"))
        list.add(ExerciseNameModel("플랭크"))
        list.add(ExerciseNameModel("러시안 트위스트"))
        list.add(ExerciseNameModel("덤벨 사이드 밴드"))
        list.add(ExerciseNameModel("싯업"))
        list.add(ExerciseNameModel("레그 레이즈"))

        // 유산소
        list.add(ExerciseNameModel("패스트 피트"))
        list.add(ExerciseNameModel("트레드 밀"))
        list.add(ExerciseNameModel("싸이클"))
        list.add(ExerciseNameModel("레터럴 스텝"))
        list.add(ExerciseNameModel("점프 스쿼트"))
        list.add(ExerciseNameModel("점핑 잭"))
        list.add(ExerciseNameModel("암 워킹"))
        list.add(ExerciseNameModel("마운틴 클라이머"))
        list.add(ExerciseNameModel("버피"))
        list.add(ExerciseNameModel("유산소 운동"))


        val rv_exerciseName: RecyclerView = rootView.findViewById(R.id.rv_exerciseName)
        val RVExerNameadapter = RecyclerAdapterExerName(list)
        rv_exerciseName.adapter = RVExerNameadapter

        // 검색 기능
        var searchViewTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {
                //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                //텍스트 입력/수정시에 호출
                override fun onQueryTextChange(s: String): Boolean {
                    RVExerNameadapter.getFilter().filter(s)
                    return false
                }
            }

        // 운동 항목 클릭 이벤트
        RVExerNameadapter.setItemClickListener(object: RecyclerAdapterExerName.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 클릭 시 이벤트 작성

                // 토스트 메시지: 운동 이름
                Toast.makeText(
                    v.context,
                    "${list[position].exerciseName}",
                    Toast.LENGTH_SHORT).show()

                // 횟수 세트/입력 팝업창 띄우기
                val exerciseCountpopupView: View = layoutInflater.inflate(R.layout.popup_exercisecount, null)
                val exerciseCountbuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                exerciseCountbuilder.setView(exerciseCountpopupView)

                val exerciseCountalertDialog: AlertDialog = exerciseCountbuilder.create();
                exerciseCountalertDialog.show();

                // 확인 버튼
                btnCancel2.setOnClickListener() {
                    // 프래그먼트 전환
                    
                    // 팝업창 해제
                    exerciseCountalertDialog.dismiss()

                }
                // 취소 버튼
                btnOk.setOnClickListener() {
                    exerciseCountalertDialog.dismiss()
                }
            }
        })

        // 커스텀 운동 추가
        val btnCustom: Button = rootView.findViewById(R.id.btnCustom)
        btnCustom.setOnClickListener() {
            // 커스텀 운동 팝업 창 띄우기
            val exerciseCustompopupView: View = layoutInflater.inflate(R.layout.popup_customexercise, null)
            val exerciseCustombuilder: AlertDialog.Builder = AlertDialog.Builder(context)
            exerciseCustombuilder.setView(exerciseCustompopupView)

            val exerciseCustomalertDialog: AlertDialog = exerciseCustombuilder.create();

            // 팝업창을 따로 만들지 않아도 사용 가능
//            AlertDialog.Builder(this)
//                .setTitle("권한이 필요합니다")
//                .setMessage("전자액자에서 사진을 선택하려면 권한이 필요합니다.")
//                .setPositiveButton("동의하기", {_, _ ->
//                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1000)
//                })
//                .setNegativeButton("취소하기",{ _,_ ->})
//                .create()
//                .show()

            exerciseCustomalertDialog.show();

            // 확인 버튼
            custom_btnCancel.setOnClickListener() {
                // 프래그먼트 전환

                // 팝업창 해제
                exerciseCustomalertDialog.dismiss()

            }
            // 취소 버튼
            custom_btnOk.setOnClickListener() {
                exerciseCustomalertDialog.dismiss()
            }
        }

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputExerciseFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}