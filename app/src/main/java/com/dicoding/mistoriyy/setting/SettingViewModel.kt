package com.dicoding.mistoriyy.setting


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mistoriyy.pref.UserPreference

import kotlinx.coroutines.launch

class SettingViewModel (private val userPreference: UserPreference) : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            _userName.value = userPreference.getUserName()
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreference.clearUserData()
        }
    }
//
//    companion object {
//        fun logout() =
//            viewModelScope.launch {
//                userPreference.clearUserData()
//            }
//
//    }
}