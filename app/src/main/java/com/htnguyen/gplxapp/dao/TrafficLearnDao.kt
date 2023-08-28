package com.htnguyen.gplxapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.htnguyen.gplxapp.model.TrafficsLearn

@Dao
interface TrafficLearnDao {
    @Query("SELECT * FROM TrafficsLearn")
    fun getAll(): LiveData<List<TrafficsLearn>>

    @Insert
    suspend fun insertAll(trafficsLearns: List<TrafficsLearn>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(trafficsLearn: TrafficsLearn)

    @Delete
    suspend fun delete(trafficsLearn: TrafficsLearn)

    @Update
    fun update(trafficsLearn: TrafficsLearn)
}