package com.htnguyen.gplxapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.htnguyen.gplxapp.database.StatusLearnDatabase
import com.htnguyen.gplxapp.database.TrafficLearnDatabase
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.repo.StatusLearnRepo
import com.htnguyen.gplxapp.repo.TrafficLearnRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LearningDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TrafficLearnRepo
    private val repositoryStatus: StatusLearnRepo

    var responseTrafficLearn: LiveData<List<TrafficsLearn>>
    var responseStatusLearn: LiveData<List<StatusLearn>>

    init {
        val trafficLearnDao = TrafficLearnDatabase.getInstance(application).trafficLearnDao()
        repository = TrafficLearnRepo(trafficLearnDao)
        responseTrafficLearn = repository.getAll()
        val statusLearnDao = StatusLearnDatabase.getInstance(application).statusLearnDao()
        repositoryStatus = StatusLearnRepo(statusLearnDao)
        responseStatusLearn = repositoryStatus.getAll()
    }

    fun updateTrafficLearn(trafficsLearn: TrafficsLearn) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(trafficsLearn)
        }
    }

    fun updateStatusLearn(statusLearn: StatusLearn) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryStatus.update(statusLearn)
        }
    }

    fun getTrafficLearnByType(type: Int) : TrafficsLearn? {
        return responseTrafficLearn.value?.first { it.id == type }
    }

}