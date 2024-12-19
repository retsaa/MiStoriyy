package com.dicoding.mistoriyy.Detail


import android.util.Log
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.StoriyResult
import kotlinx.coroutines.launch

class DetailViewModel (private val detailStoriyRepository: DetailStoriyRepository) : ViewModel() {
    private val _detailStory = MutableLiveData<StoriyResult<Story?>>()
    val storyDetail: LiveData<StoriyResult<Story?>> get() = _detailStory

    fun detailStory(storyId: String) {
        viewModelScope.launch {
            try {
                val result = detailStoriyRepository.detailSutori(storyId)
                _detailStory.value = result
            } catch (e: Exception) {
                _detailStory.value = StoriyResult.Error(e.message.toString())
                Log.e("DetailViewModel", "Error: ${e.message.toString()}")
            }
        }
    }
}