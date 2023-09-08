package com.htnguyen.gplxapp.repo

import androidx.lifecycle.LiveData
import com.htnguyen.gplxapp.dao.StatusLearnDao
import com.htnguyen.gplxapp.dao.TrafficLearnDao
import com.htnguyen.gplxapp.model.StatusLearn

class StatusLearnRepo(private val statusLearnDao: StatusLearnDao) {
    suspend fun insert(statusLearn: StatusLearn) {
        statusLearnDao.add(statusLearn)
    }
    suspend fun update(statusLearn: StatusLearn) {
        statusLearnDao.update(statusLearn)
    }

    fun getAll() : LiveData<List<StatusLearn>> = statusLearnDao.getAll()

    fun getByType(type: Int) : LiveData<List<StatusLearn>> = statusLearnDao.getByType(type)

}