package com.htnguyen.gplxapp.view.fragment.learning

import android.app.Application
import androidx.lifecycle.*
import com.htnguyen.gplxapp.database.ExamDatabase
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.model.Exam
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.repo.ExamRepo
import com.htnguyen.gplxapp.repo.TrafficLearnRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExamViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExamRepo

    var responseExam: LiveData<List<Exam>>

    init {
        val examDao = ExamDatabase.getInstance(application).examDao()
        repository = ExamRepo(examDao)
        responseExam = repository.getAll()
    }

    fun addData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(
                Exam(
                    1,
                    "Đề số 1",
                    "25 câu/19 phút",
                    "0",
                    0
                )
            )
            repository.insert(
                Exam(
                    2,
                    "Đề số 2",
                    "25 câu/19 phút",
                    "0",
                    0
                )
            )
            repository.insert(
                Exam(
                    3,
                    "Đề số 3",
                    "25 câu/19 phút",
                    "0",
                    0,
                )
            )
            repository.insert(
                Exam(
                    4,
                    "Đề số 4",
                    "25 câu/19 phút",
                    "0",
                    0
                )
            )
            repository.insert(
                Exam(
                    5,
                    "Đề số 5",
                    "25 câu/19 phút",
                    "0",
                    0
                )
            )
            repository.insert(
                Exam(
                    6,
                    "Đề số 6",
                    "25 câu/19 phút",
                    "0",
                    0
                )
            )
            repository.insert(
                Exam(
                    7,
                    "Đề số 7",
                    "25 câu/19 phút",
                    "0",
                    0
                )
            )
            repository.insert(
                Exam(
                    8,
                    "Đề số 8",
                    "25 câu/19 phút",
                    "0",
                    0
                )
            )
        }
    }


}