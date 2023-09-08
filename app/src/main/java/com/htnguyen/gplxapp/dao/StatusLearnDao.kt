package com.htnguyen.gplxapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.TrafficsLearn

@Dao
interface StatusLearnDao {
    @Query("SELECT * FROM StatusLearn")
    fun getAll(): LiveData<List<StatusLearn>>

    @Query("SELECT * FROM StatusLearn WHERE idType=:type")
    fun getByType(type: Int): LiveData<List<StatusLearn>>

    @Insert
    suspend fun insertAll(statusLearn: List<StatusLearn>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(statusLearn: StatusLearn)

    @Delete
    suspend fun delete(statusLearn: StatusLearn)

    @Update
    fun update(statusLearn: StatusLearn)
}