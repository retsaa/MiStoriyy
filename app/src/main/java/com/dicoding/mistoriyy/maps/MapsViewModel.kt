package com.dicoding.mistoriyy.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mistoriyy.data.di.StoriyResult
import com.dicoding.mistoriyy.storiyy.ListStoriyItem
import com.dicoding.mistoriyy.storiyy.StoriyLocationRepository
import kotlinx.coroutines.launch

class MapsViewModel (private val storiyLocationRepository: StoriyLocationRepository ) : ViewModel() {
    private val _location = MutableLiveData<StoriyResult<List<ListStoriyItem?>>>()
    val location get() = _location

    fun getStoriyLocation() {
        viewModelScope.launch {
            try {
                val result = storiyLocationRepository.getStoriyLocation()
                _location.value = result
                Log.d("MapsViewModel", "getStoryLocation: $result")
            } catch (e: Exception) {
                _location.value = StoriyResult.Error(e.message.toString())
                Log.e("MapsViewModel", "Error: &{e.message}")
            }
        }
    }
}