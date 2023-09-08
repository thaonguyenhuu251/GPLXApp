package com.htnguyen.gplxapp.repo

import androidx.lifecycle.LiveData
import com.htnguyen.gplxapp.dao.TrafficLearnDao
import com.htnguyen.gplxapp.model.TrafficsLearn

class TrafficLearnRepo(private val trafficLearnDao: TrafficLearnDao) {
    suspend fun insert(trafficLearn: TrafficsLearn) {
        trafficLearnDao.add(trafficLearn)
    }
    suspend fun update(trafficLearn: TrafficsLearn) {
        trafficLearnDao.update(trafficLearn)
    }


    fun getAll() : LiveData<List<TrafficsLearn>> = trafficLearnDao.getAll()

}