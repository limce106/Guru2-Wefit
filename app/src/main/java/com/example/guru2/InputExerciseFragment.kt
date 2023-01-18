package com.example.guru2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input_exercise.*

class InputExerciseFragment : Fragment() {

    lateinit var myHelper: myDBHelper
    lateinit var sqlDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val list = ArrayList<ExerciseListModel>()

        //가슴
        list.add(ExerciseListModel("니 푸시업"))
        list.add(ExerciseListModel("중량 푸시업"))
        list.add(ExerciseListModel("중량 딥스"))
        list.add(ExerciseListModel("인클라인 벤치프레스 머신"))
        list.add(ExerciseListModel("덤벨 풀오버"))
        list.add(ExerciseListModel("푸시업"))
        list.add(ExerciseListModel("펙덱 플라이 머신"))
        list.add(ExerciseListModel("인클라인 덤벨 플라이"))
        list.add(ExerciseListModel("케이블 크로스 오버"))
        list.add(ExerciseListModel("체스트 프레스 머신"))
        list.add(ExerciseListModel("덤벨 플라이"))
        list.add(ExerciseListModel("딥스"))
        list.add(ExerciseListModel("인클라인 벤치프레스"))
        list.add(ExerciseListModel("인클라인 덤벨 프레스"))
        list.add(ExerciseListModel("덤벨 프레스"))
        list.add(ExerciseListModel("푸쉬업"))
        list.add(ExerciseListModel("벤치프레스"))

        // 등
        list.add(ExerciseListModel("어시스트 풀업 머신"))
        list.add(ExerciseListModel("중량 하이퍼 익스텐션"))
        list.add(ExerciseListModel("백 익스텐션"))
        list.add(ExerciseListModel("중량 풀업"))
        list.add(ExerciseListModel("인버티드 로우"))
        list.add(ExerciseListModel("바벨 풀오버"))
        list.add(ExerciseListModel("원암 덤벨 로우"))
        list.add(ExerciseListModel("시티드 케이블 로우"))
        list.add(ExerciseListModel("친 업"))
        list.add(ExerciseListModel("백 익스텐션 머신"))
        list.add(ExerciseListModel("굿모닝 엑서사이즈"))
        list.add(ExerciseListModel("펜들레이 로우"))
        list.add(ExerciseListModel("시티드 로우 머신"))
        list.add(ExerciseListModel("랫 풀 다운"))
        list.add(ExerciseListModel("바벨 로우"))
        list.add(ExerciseListModel("덤벨 로우"))
        list.add(ExerciseListModel("풀업"))

        // 어깨
        list.add(ExerciseListModel("덤벨 업라이트 로우"))
        list.add(ExerciseListModel("이지바 업라이트 로우"))
        list.add(ExerciseListModel("아놀드 덤벨 프레스"))
        list.add(ExerciseListModel("숄더 프레스 머신"))
        list.add(ExerciseListModel("케이블 리버스 플라이"))
        list.add(ExerciseListModel("벤트오버 덤벨 레터럴 레이즈"))
        list.add(ExerciseListModel("바벨 업라이트 로우"))
        list.add(ExerciseListModel("페이스 풀"))
        list.add(ExerciseListModel("비하인드 넥 프레스"))
        list.add(ExerciseListModel("덤벨 슈러그"))
        list.add(ExerciseListModel("덤벨 프론트 레이즈"))
        list.add(ExerciseListModel("덤벨 숄더 프레스"))
        list.add(ExerciseListModel("덤벨 레터럴 레이즈"))
        list.add(ExerciseListModel("오버헤드 프레스"))

        // 팔
        list.add(ExerciseListModel("스컬 크러셔"))
        list.add(ExerciseListModel("바벨 리스트 컬"))
        list.add(ExerciseListModel("시티드 덤벨 익스텐션"))
        list.add(ExerciseListModel("케이블 삼두 익스텐션"))
        list.add(ExerciseListModel("이지바 컬"))
        list.add(ExerciseListModel("케이블 푸시 다운"))
        list.add(ExerciseListModel("덤벨 해머 컬"))
        list.add(ExerciseListModel("덤벨 킥백"))
        list.add(ExerciseListModel("덤벨 리스트 컬"))
        list.add(ExerciseListModel("덤벨 삼두 익스텐션"))
        list.add(ExerciseListModel("바벨 컬"))
        list.add(ExerciseListModel("덤벨 컬"))

        // 하체
        list.add(ExerciseListModel("덩키 킥"))
        list.add(ExerciseListModel("아웃 싸이 머신"))
        list.add(ExerciseListModel("힙 쓰러스트"))
        list.add(ExerciseListModel("중량 힙 쓰러스트"))
        list.add(ExerciseListModel("덤벨 스플릿 스쿼트"))
        list.add(ExerciseListModel("중량 스텝업"))
        list.add(ExerciseListModel("고블릿 스쿼트"))
        list.add(ExerciseListModel("스텝업"))
        list.add(ExerciseListModel("저처 스쿼트"))
        list.add(ExerciseListModel("바벨 스플릿 스쿼트"))
        list.add(ExerciseListModel("이너 싸이 머신"))
        list.add(ExerciseListModel("에어 스쿼트"))
        list.add(ExerciseListModel("루마니안 데드리프트"))
        list.add(ExerciseListModel("런지"))
        list.add(ExerciseListModel("스탠딩 카프 레이즈"))
        list.add(ExerciseListModel("스모 데드리프트"))
        list.add(ExerciseListModel("덤벨 런지"))
        list.add(ExerciseListModel("레그 프레스"))
        list.add(ExerciseListModel("레그 컬"))
        list.add(ExerciseListModel("레그 익스텐션"))
        list.add(ExerciseListModel("프론트 스쿼트"))
        list.add(ExerciseListModel("컨벤셔널 데드리프트"))
        list.add(ExerciseListModel("바벨 백스쿼트"))

        //코어
        list.add(ExerciseListModel("버드 독"))
        list.add(ExerciseListModel("벤치 디클라인 크런치"))
        list.add(ExerciseListModel("슈퍼맨"))
        list.add(ExerciseListModel("니 업"))
        list.add(ExerciseListModel("핸즈 워킹"))
        list.add(ExerciseListModel("스탠딩 사이드 크런치"))
        list.add(ExerciseListModel("데드 버그"))
        list.add(ExerciseListModel("롤 업"))
        list.add(ExerciseListModel("토즈 투 바"))
        list.add(ExerciseListModel("힐 터치"))
        list.add(ExerciseListModel("할로우 포지션"))
        list.add(ExerciseListModel("할로우 락"))
        list.add(ExerciseListModel("행잉 레그 레이즈"))
        list.add(ExerciseListModel("크런치"))
        list.add(ExerciseListModel("브이 업"))
        list.add(ExerciseListModel("바이시클 크런치"))
        list.add(ExerciseListModel("롤 아웃"))
        list.add(ExerciseListModel("플랭크"))
        list.add(ExerciseListModel("러시안 트위스트"))
        list.add(ExerciseListModel("덤벨 사이드 밴드"))
        list.add(ExerciseListModel("싯업"))
        list.add(ExerciseListModel("레그 레이즈"))

        // 유산소
        list.add(ExerciseListModel("패스트 피트"))
        list.add(ExerciseListModel("트레드 밀"))
        list.add(ExerciseListModel("싸이클"))
        list.add(ExerciseListModel("레터럴 스텝"))
        list.add(ExerciseListModel("점프 스쿼트"))
        list.add(ExerciseListModel("점핑 잭"))
        list.add(ExerciseListModel("암 워킹"))
        list.add(ExerciseListModel("마운틴 클라이머"))
        list.add(ExerciseListModel("버피"))
        list.add(ExerciseListModel("유산소 운동"))

        val adapter = RecyclerAdapterExerName(list)
        rv_exerciseName.adapter = adapter

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

    inner class myDBHelper(context: Context): SQLiteOpenHelper(context,"groupDB", null, 1){
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEGER);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS groupTBL")
            onCreate(db)
        }

    }
}