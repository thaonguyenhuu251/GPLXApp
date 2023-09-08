package com.htnguyen.gplxapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.repo.TrafficLearnRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LearningDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TrafficLearnRepo

    var responseTrafficLearn: LiveData<List<TrafficsLearn>>

    init {
        val trafficLearnDao = TrafficLearnDatabase.getInstance(application).trafficLearnDao()
        repository = TrafficLearnRepo(trafficLearnDao)
        responseTrafficLearn = repository.getAll()
    }

    fun updateData(trafficsLearn: TrafficsLearn) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(trafficsLearn)
        }
    }

}