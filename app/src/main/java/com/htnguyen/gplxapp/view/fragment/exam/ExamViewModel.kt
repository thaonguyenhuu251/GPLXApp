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
            repository.insert(Exam(1, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", 60, 0))
            repository.insert(Exam(2, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", 60, 1))
            repository.insert(Exam(3, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", 60, 2))
            repository.insert(Exam(4, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", 60, 3))
        }
    }


}