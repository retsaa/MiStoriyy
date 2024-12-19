package com.dicoding.mistoriyy.splashscreen


import androidx.lifecycle.*
import com.dicoding.mistoriyy.pref.UserPreference
import kotlinx.coroutines.launch

class SplashViewModel (private val userPreference: UserPreference) : ViewModel() {
    private val _userToken = MutableLiveData<Boolean>()
    val userToken: LiveData<Boolean> get() = _userToken

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch {
            val token = userPreference.getUserToken()
            _userToken.value = token.isNotEmpty()
        }
    }
}