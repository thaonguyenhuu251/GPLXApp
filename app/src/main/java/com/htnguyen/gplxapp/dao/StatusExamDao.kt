package com.htnguyen.gplxapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.htnguyen.gplxapp.model.StatusExam
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.TrafficsLearn

@Dao
interface StatusExamDao {
    @Query("SELECT * FROM StatusExam")
    fun getAll(): LiveData<List<StatusExam>>

    @Query("SELECT * FROM StatusExam WHERE idType=:type")
    fun getByType(type: Int): LiveData<List<StatusExam>>

    @Insert
    suspend fun insertAll(statusExam: List<StatusExam>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(statusExam: StatusExam)

    @Delete
    suspend fun delete(statusExam: StatusExam)

    @Update
    fun update(statusExam: StatusExam)
}