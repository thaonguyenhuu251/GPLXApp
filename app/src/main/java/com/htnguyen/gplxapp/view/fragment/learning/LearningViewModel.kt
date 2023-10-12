package com.htnguyen.gplxapp.view.fragment.learning

import android.app.Application
import androidx.lifecycle.*
import com.htnguyen.gplxapp.base.utils.SharePreference
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

}