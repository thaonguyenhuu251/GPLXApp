package com.htnguyen.gplxapp.repo

import androidx.lifecycle.LiveData
import com.htnguyen.gplxapp.dao.CompetitionDao
import com.htnguyen.gplxapp.model.Competition

class CompetitionRepo(private val competitionDao: CompetitionDao) {
    suspend fun insert(statusLearn: Competition) {
        competitionDao.add(statusLearn)
    }
    suspend fun update(statusLearn: Competition) {
        competitionDao.update(statusLearn)
    }

    fun getAll() : LiveData<List<Competition>> = competitionDao.getAll()

    fun getByType(type: Int) : LiveData<List<Competition>> = competitionDao.getByType(type)

}