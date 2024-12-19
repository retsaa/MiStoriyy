package com.dicoding.mistoriyy.home

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.StoriyResult
import com.dicoding.mistoriyy.storiyy.ListStoriyItem
import com.dicoding.mistoriyy.storiyy.StoriyRepository
import kotlinx.coroutines.launch

class HomeViewModel (private val storiyRepository: StoriyRepository): ViewModel() {
    private val _story = MutableLiveData<StoriyResult<List<ListStoriyItem?>>>()
    val story: LiveData<StoriyResult<List<ListStoriyItem?>>>
        get() = _story

    fun findStory() {
        viewModelScope.launch {
            try {
                val result = storiyRepository.getStoriy()
                _story.value = result
            } catch (e: Exception) {
                _story.value = StoriyResult.Error(e.message.toString())
                Log.e("HomeViewModel", "Error: ${e.message.toString()}")
            }
        }
    }
}