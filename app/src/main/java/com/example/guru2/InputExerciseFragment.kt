package com.example.guru2

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input_exercise.*
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

        val list = ArrayList<DataExerciseName>()

        //가슴
        list.add(DataExerciseName("니 푸시업"))
        list.add(DataExerciseName("중량 푸시업"))
        list.add(DataExerciseName("중량 딥스"))
        list.add(DataExerciseName("인클라인 벤치프레스 머신"))
        list.add(DataExerciseName("덤벨 풀오버"))
        list.add(DataExerciseName("푸시업"))
        list.add(DataExerciseName("펙덱 플라이 머신"))
        list.add(DataExerciseName("인클라인 덤벨 플라이"))
        list.add(DataExerciseName("케이블 크로스 오버"))
        list.add(DataExerciseName("체스트 프레스 머신"))
        list.add(DataExerciseName("덤벨 플라이"))
        list.add(DataExerciseName("딥스"))
        list.add(DataExerciseName("인클라인 벤치프레스"))
        list.add(DataExerciseName("인클라인 덤벨 프레스"))
        list.add(DataExerciseName("덤벨 프레스"))
        list.add(DataExerciseName("푸쉬업"))
        list.add(DataExerciseName("벤치프레스"))

        // 등
        list.add(DataExerciseName("어시스트 풀업 머신"))
        list.add(DataExerciseName("중량 하이퍼 익스텐션"))
        list.add(DataExerciseName("백 익스텐션"))
        list.add(DataExerciseName("중량 풀업"))
        list.add(DataExerciseName("인버티드 로우"))
        list.add(DataExerciseName("바벨 풀오버"))
        list.add(DataExerciseName("원암 덤벨 로우"))
        list.add(DataExerciseName("시티드 케이블 로우"))
        list.add(DataExerciseName("친 업"))
        list.add(DataExerciseName("백 익스텐션 머신"))
        list.add(DataExerciseName("굿모닝 엑서사이즈"))
        list.add(DataExerciseName("펜들레이 로우"))
        list.add(DataExerciseName("시티드 로우 머신"))
        list.add(DataExerciseName("랫 풀 다운"))
        list.add(DataExerciseName("바벨 로우"))
        list.add(DataExerciseName("덤벨 로우"))
        list.add(DataExerciseName("풀업"))

        // 어깨
        list.add(DataExerciseName("덤벨 업라이트 로우"))
        list.add(DataExerciseName("이지바 업라이트 로우"))
        list.add(DataExerciseName("아놀드 덤벨 프레스"))
        list.add(DataExerciseName("숄더 프레스 머신"))
        list.add(DataExerciseName("케이블 리버스 플라이"))
        list.add(DataExerciseName("벤트오버 덤벨 레터럴 레이즈"))
        list.add(DataExerciseName("바벨 업라이트 로우"))
        list.add(DataExerciseName("페이스 풀"))
        list.add(DataExerciseName("비하인드 넥 프레스"))
        list.add(DataExerciseName("덤벨 슈러그"))
        list.add(DataExerciseName("덤벨 프론트 레이즈"))
        list.add(DataExerciseName("덤벨 숄더 프레스"))
        list.add(DataExerciseName("덤벨 레터럴 레이즈"))
        list.add(DataExerciseName("오버헤드 프레스"))

        // 팔
        list.add(DataExerciseName("스컬 크러셔"))
        list.add(DataExerciseName("바벨 리스트 컬"))
        list.add(DataExerciseName("시티드 덤벨 익스텐션"))
        list.add(DataExerciseName("케이블 삼두 익스텐션"))
        list.add(DataExerciseName("이지바 컬"))
        list.add(DataExerciseName("케이블 푸시 다운"))
        list.add(DataExerciseName("덤벨 해머 컬"))
        list.add(DataExerciseName("덤벨 킥백"))
        list.add(DataExerciseName("덤벨 리스트 컬"))
        list.add(DataExerciseName("덤벨 삼두 익스텐션"))
        list.add(DataExerciseName("바벨 컬"))
        list.add(DataExerciseName("덤벨 컬"))

        // 하체
        list.add(DataExerciseName("덩키 킥"))
        list.add(DataExerciseName("아웃 싸이 머신"))
        list.add(DataExerciseName("힙 쓰러스트"))
        list.add(DataExerciseName("중량 힙 쓰러스트"))
        list.add(DataExerciseName("덤벨 스플릿 스쿼트"))
        list.add(DataExerciseName("중량 스텝업"))
        list.add(DataExerciseName("고블릿 스쿼트"))
        list.add(DataExerciseName("스텝업"))
        list.add(DataExerciseName("저처 스쿼트"))
        list.add(DataExerciseName("바벨 스플릿 스쿼트"))
        list.add(DataExerciseName("이너 싸이 머신"))
        list.add(DataExerciseName("에어 스쿼트"))
        list.add(DataExerciseName("루마니안 데드리프트"))
        list.add(DataExerciseName("런지"))
        list.add(DataExerciseName("스탠딩 카프 레이즈"))
        list.add(DataExerciseName("스모 데드리프트"))
        list.add(DataExerciseName("덤벨 런지"))
        list.add(DataExerciseName("레그 프레스"))
        list.add(DataExerciseName("레그 컬"))
        list.add(DataExerciseName("레그 익스텐션"))
        list.add(DataExerciseName("프론트 스쿼트"))
        list.add(DataExerciseName("컨벤셔널 데드리프트"))
        list.add(DataExerciseName("바벨 백스쿼트"))

        //코어
        list.add(DataExerciseName("버드 독"))
        list.add(DataExerciseName("벤치 디클라인 크런치"))
        list.add(DataExerciseName("슈퍼맨"))
        list.add(DataExerciseName("니 업"))
        list.add(DataExerciseName("핸즈 워킹"))
        list.add(DataExerciseName("스탠딩 사이드 크런치"))
        list.add(DataExerciseName("데드 버그"))
        list.add(DataExerciseName("롤 업"))
        list.add(DataExerciseName("토즈 투 바"))
        list.add(DataExerciseName("힐 터치"))
        list.add(DataExerciseName("할로우 포지션"))
        list.add(DataExerciseName("할로우 락"))
        list.add(DataExerciseName("행잉 레그 레이즈"))
        list.add(DataExerciseName("크런치"))
        list.add(DataExerciseName("브이 업"))
        list.add(DataExerciseName("바이시클 크런치"))
        list.add(DataExerciseName("롤 아웃"))
        list.add(DataExerciseName("플랭크"))
        list.add(DataExerciseName("러시안 트위스트"))
        list.add(DataExerciseName("덤벨 사이드 밴드"))
        list.add(DataExerciseName("싯업"))
        list.add(DataExerciseName("레그 레이즈"))

        // 유산소
        list.add(DataExerciseName("패스트 피트"))
        list.add(DataExerciseName("트레드 밀"))
        list.add(DataExerciseName("싸이클"))
        list.add(DataExerciseName("레터럴 스텝"))
        list.add(DataExerciseName("점프 스쿼트"))
        list.add(DataExerciseName("점핑 잭"))
        list.add(DataExerciseName("암 워킹"))
        list.add(DataExerciseName("마운틴 클라이머"))
        list.add(DataExerciseName("버피"))
        list.add(DataExerciseName("유산소 운동"))

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
                    view!!.context,
                    "${list[position].exerciseName}",
                    Toast.LENGTH_SHORT).show()

                // 횟수 세트/입력 팝업창 띄우기
                val exerciseCountpopupView: View = layoutInflater.inflate(R.layout.popup_exercisecount, null)
                val exerciseCountbuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                exerciseCountbuilder.setView(exerciseCountpopupView)

                val exerciseCountalertDialog: AlertDialog = exerciseCountbuilder.create();
                exerciseCountalertDialog.show();

                // 확인 버튼
                btnOk.setOnClickListener() {
                    // 프래그먼트 전환
                    
                    // 팝업창 해제
                    exerciseCountalertDialog.dismiss()

                }
                // 취소 버튼
                btnCancel.setOnClickListener() {
                    exerciseCountalertDialog.dismiss()
                }
            }
        })

        // 커스텀 운동 추가
        btnCustom.setOnClickListener() {
            // 커스텀 운동 팝업 창 띄우기
            val exerciseCustompopupView: View = layoutInflater.inflate(R.layout.popup_customexercise, null)
            val exerciseCustombuilder: AlertDialog.Builder = AlertDialog.Builder(context)
            exerciseCustombuilder.setView(exerciseCustompopupView)

            val exerciseCustomalertDialog: AlertDialog = exerciseCustombuilder.create();
            exerciseCustomalertDialog.show();

            // 확인 버튼
            custom_btnOk.setOnClickListener() {
                // 프래그먼트 전환
//                supportFragmentManager().beginTransaction()
//                    .replace(R.id.framelayout, fragment_main).commit()
                // 팝업창 해제
                exerciseCustomalertDialog.dismiss()

            }
            // 취소 버튼
            custom_btnCancel.setOnClickListener() {
                exerciseCustomalertDialog.dismiss()
            }
        }

        return inflater.inflate(R.layout.fragment_input_exercise, container, false)
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