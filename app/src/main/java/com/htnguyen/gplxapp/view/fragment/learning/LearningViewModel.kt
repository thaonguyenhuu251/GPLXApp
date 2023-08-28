package com.htnguyen.gplxapp.view.fragment.learning

import android.app.Application
import androidx.lifecycle.*
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.repo.TrafficLearnRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LearningViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TrafficLearnRepo

    var responseTrafficLearn: LiveData<List<TrafficsLearn>>

    init {
        val trafficLearnDao = TrafficLearnDatabase.getInstance(application).trafficLearnDao()
        repository = TrafficLearnRepo(trafficLearnDao)
        responseTrafficLearn = repository.getAll()
    }

    fun addData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(TrafficsLearn(1, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", "https://www.vba.vic.gov.au/__data/assets/image/0003/103971/warning-sign.png", 60, 0))
            repository.insert(TrafficsLearn(2, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", "https://", 60, 0))
            repository.insert(TrafficsLearn(3, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", "https://", 60, 0))
            repository.insert(TrafficsLearn(4, "80 CÂU HỎI ĐIỂM LIỆT", "80 câu hoir điểm liệt", "https://", 60, 0))
        }
    }


}