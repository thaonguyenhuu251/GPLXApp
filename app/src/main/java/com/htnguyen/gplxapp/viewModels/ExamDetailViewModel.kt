package com.htnguyen.gplxapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.htnguyen.gplxapp.database.ExamDatabase
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.model.Exam
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.repo.ExamRepo
import com.htnguyen.gplxapp.repo.TrafficLearnRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExamDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExamRepo

    var responseExam: LiveData<List<Exam>>

    init {
        val examDao = ExamDatabase.getInstance(application).examDao()
        repository = ExamRepo(examDao)
        responseExam = repository.getAll()
    }

}