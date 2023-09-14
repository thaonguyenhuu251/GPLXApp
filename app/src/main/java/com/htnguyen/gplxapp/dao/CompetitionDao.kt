package com.htnguyen.gplxapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.htnguyen.gplxapp.model.Competition

@Dao
interface CompetitionDao {
    @Query("SELECT * FROM Competition")
    fun getAll(): LiveData<List<Competition>>

    @Query("SELECT * FROM Competition WHERE idType=:type")
    fun getByType(type: Int): LiveData<List<Competition>>

    @Insert
    suspend fun insertAll(statusLearn: List<Competition>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(competition: Competition)

    @Delete
    suspend fun delete(competition: Competition)

    @Update
    fun update(competition: Competition)
}