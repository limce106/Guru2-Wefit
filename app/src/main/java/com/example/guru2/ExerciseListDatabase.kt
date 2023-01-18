package layout

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.guru2.DataExerciseName

@Database(entities = [DataExerciseName::class], version = 1)
abstract class ExerciseListDatabase : RoomDatabase() {
    abstract fun exerciseNameDao() : ExerciseListDao
    companion object {

        private var INSTANCE: ExerciseListDatabase? = null

        // 여러 스레드가 접근하지 못하도록 synchronized로 설정
        fun getInstance(context: Context): ExerciseListDatabase? {

            if (INSTANCE == null) {
                synchronized(ExerciseListDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ExerciseListDatabase::class.java,
                        "tb_todo"
                    )
                        .fallbackToDestructiveMigration()  //데이터베이스가 갱신될 때 기존의 테이블을 버리고 새로 사용하도록 설정
                        .build()
                }
            }
            return INSTANCE
        }

    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }
}