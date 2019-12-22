package com.sridhar.crosswordpuzzle.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _canLaunchActivity = MutableLiveData<Boolean>(false)
    internal val canLaunchActivity: LiveData<Boolean> = _canLaunchActivity

    init {
        viewModelScope.launch(Dispatchers.Default) {
            delay(3000)
            _canLaunchActivity.postValue(true)
        }
    }
}