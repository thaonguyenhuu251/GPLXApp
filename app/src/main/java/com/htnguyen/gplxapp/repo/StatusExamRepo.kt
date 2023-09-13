package com.htnguyen.gplxapp.repo

import androidx.lifecycle.LiveData
import com.htnguyen.gplxapp.dao.StatusExamDao
import com.htnguyen.gplxapp.dao.StatusLearnDao
import com.htnguyen.gplxapp.dao.TrafficLearnDao
import com.htnguyen.gplxapp.model.StatusExam
import com.htnguyen.gplxapp.model.StatusLearn

class StatusExamRepo(private val statusExamDao: StatusExamDao) {
    suspend fun insert(statusExam: StatusExam) {
        statusExamDao.add(statusExam)
    }
    suspend fun update(statusExam: StatusExam) {
        statusExamDao.update(statusExam)
    }

    fun getAll() : LiveData<List<StatusExam>> = statusExamDao.getAll()

    fun getByType(type: Int) : LiveData<List<StatusExam>> = statusExamDao.getByType(type)

}