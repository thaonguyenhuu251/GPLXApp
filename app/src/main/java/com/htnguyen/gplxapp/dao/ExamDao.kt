package com.htnguyen.gplxapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.htnguyen.gplxapp.model.Exam

@Dao
interface ExamDao {
    @Query("SELECT * FROM Exam")
    fun getAll(): LiveData<List<Exam>>

    @Insert
    suspend fun insertAll(examTest: List<Exam>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(examTest: Exam)

    @Delete
    suspend fun delete(examTest: Exam)

    @Update
    fun update(examTest: Exam)
}