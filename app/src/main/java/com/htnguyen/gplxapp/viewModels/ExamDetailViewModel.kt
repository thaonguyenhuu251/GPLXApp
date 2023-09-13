package com.htnguyen.gplxapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.htnguyen.gplxapp.database.ExamDatabase
import com.htnguyen.gplxapp.database.StatusExamDatabase
import com.htnguyen.gplxapp.database.StatusLearnDatabase
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.model.*
import com.htnguyen.gplxapp.repo.ExamRepo
import com.htnguyen.gplxapp.repo.StatusExamRepo
import com.htnguyen.gplxapp.repo.StatusLearnRepo
import com.htnguyen.gplxapp.repo.TrafficLearnRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExamDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExamRepo
    private val repositoryStatusExam: StatusExamRepo

    var responseExam: LiveData<List<Exam>>
    var responseStatusExam: LiveData<List<StatusExam>>

    init {
        val examDao = ExamDatabase.getInstance(application).examDao()
        repository = ExamRepo(examDao)
        responseExam = repository.getAll()
        val statusExamDao = StatusExamDatabase.getInstance(application).statusExamDao()
        repositoryStatusExam = StatusExamRepo(statusExamDao)
        responseStatusExam = repositoryStatusExam.getAll()
    }

    fun updateExam(exam: Exam) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(exam)
        }
    }

    fun updateStatusLearn(statusLearn: StatusExam) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryStatusExam.update(statusLearn)
        }
    }

    fun getTrafficLearnByType(type: Int) : Exam? {
        return responseExam.value?.first { it.id == type }
    }

}