package com.htnguyen.gplxapp.repo

import androidx.lifecycle.LiveData
import com.htnguyen.gplxapp.dao.ExamDao
import com.htnguyen.gplxapp.model.Exam
import com.htnguyen.gplxapp.model.TrafficsLearn

class ExamRepo(private val examDao: ExamDao) {
    suspend fun insert(examTest: Exam) {
        examDao.add(examTest)
    }

    suspend fun update(exam: Exam) {
        examDao.update(exam)
    }

    fun getAll() : LiveData<List<Exam>> = examDao.getAll()

}