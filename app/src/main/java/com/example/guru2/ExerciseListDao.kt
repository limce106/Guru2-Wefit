package layout

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.guru2.DataExerciseName

@Dao
interface ExerciseListDao {
    @Query("SELECT * FROM tb_exerciseName ORDER BY EXERCISENAME ASC")
    fun getexerciseNameAll(): LiveData<List<DataExerciseName>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  //@Insert 의 onConflict = : 중복된 데이터의 경우 어떻게 처리할 것인지에 대한 처리를 지정
    fun insert(todo: DataExerciseName)

    @Delete
    fun delete(todo: DataExerciseName)
}