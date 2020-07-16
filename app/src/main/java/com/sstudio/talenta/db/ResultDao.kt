package com.sstudio.talenta.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sstudio.talenta.model.Result
import io.reactivex.Flowable

@Dao
interface ResultDao {

    @Query("select * from result")
    fun getAllResult(): Flowable<List<Result>>

    @Insert
    fun insertResult(result: Result)

//    @Query("SELECT * FROM students WHERE id = :id ")
//    fun getById(id: String): Flowable<Student>

//    @Delete
//    fun delete(student: Student)

    @Query("DELETE FROM result WHERE id =:mId")
    fun deleteResult(mId: Int)

    @Query("UPDATE result SET name =:mName WHERE id =:mId")
    fun updateResult(mName: Int, mId: String)
}