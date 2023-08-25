package com.htnguyen.gplxapp.view.fragment.introduction_app

import androidx.lifecycle.*
import com.bumptech.glide.load.HttpException
import com.htnguyen.gplxapp.model.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class IntroductionAppViewModel() : ViewModel() {
    var isLastPage: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val userList = MutableLiveData<List<UsersResponse>>()
    val errorMessage = MutableLiveData<String>()

    fun vmGetUserList () {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {

                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            errorMessage.postValue("Network Error")
                        }
                        is HttpException -> {

                        }
                        else -> {
                            errorMessage.postValue("Uknown error")
                        }
                    }
                }
            }
        }
    }

}