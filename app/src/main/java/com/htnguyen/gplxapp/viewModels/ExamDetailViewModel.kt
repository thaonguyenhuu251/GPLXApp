package com.htnguyen.gplxapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.htnguyen.gplxapp.database.*
import com.htnguyen.gplxapp.model.*
import com.htnguyen.gplxapp.repo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExamDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExamRepo
    private val repositoryStatusExam: StatusExamRepo
    private val repositoryCompetition: CompetitionRepo

    var responseExam: LiveData<List<Exam>>
    var responseStatusExam: LiveData<List<StatusExam>>
    var responseCompetition: LiveData<List<Competition>>

    init {
        val examDao = ExamDatabase.getInstance(application).examDao()
        repository = ExamRepo(examDao)
        responseExam = repository.getAll()
        val statusExamDao = StatusExamDatabase.getInstance(application).statusExamDao()
        repositoryStatusExam = StatusExamRepo(statusExamDao)
        responseStatusExam = repositoryStatusExam.getAll()
        val competitionDao = CompetitionDatabase.getInstance(application).competitionDao()
        repositoryCompetition = CompetitionRepo(competitionDao)
        responseCompetition = repositoryCompetition.getAll()
    }

    fun updateExam(exam: Exam) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(exam)
        }
    }

    fun updateStatusExam(statusLearn: StatusExam) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryStatusExam.update(statusLearn)
        }
    }

    fun updateCompetition(competition : Competition) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryCompetition.update(competition)
        }
    }

    fun getTrafficLearnByType(type: Int) : Exam? {
        return responseExam.value?.first { it.id == type }
    }

}