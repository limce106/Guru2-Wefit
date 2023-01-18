package com.example.guru2

import android.app.Application
import androidx.lifecycle.LiveData
import layout.ExerciseListDao
import layout.ExerciseListDatabase

class ExerciseListRepository(application: Application) {
    private var enDatabase: ExerciseListDatabase = ExerciseListDatabase.getInstance(application)!!
    private var enDao: ExerciseListDao = enDatabase.exerciseNameDao()
    private var enItems: LiveData<List<DataExerciseName>> = enDao.getexerciseNameAll()

    fun getTodoListAll(): LiveData<List<DataExerciseName>> {
        return enItems
    }

    fun insert(todoModel: DataExerciseName) {
        try {
            val thread =
                Thread(Runnable {  //별도 스레드를 통해 Room 데이터에 접근해야한다. 연산 시간이 오래 걸리는 작업은 메인 쓰레드가 아닌 별도의 쓰레드에서 하도록 되어있다. 그렇지 않으면 런타임에러 발생.
                    enDao.insert(todoModel)
                }).start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun delete(todoModel: DataExerciseName) {
        try {
            val thread = Thread(Runnable {
                enDao.delete(todoModel)
            })
            thread.start()
        } catch (e: Exception) {
        }
    }

}