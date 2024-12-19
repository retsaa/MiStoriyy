package com.dicoding.mistoriyy.storiyy

import android.util.Log
import com.dicoding.mistoriyy.data.di.StoriyResult
import com.dicoding.mistoriyy.retrofit.ApiService

class StoriyLocationRepository private constructor(private val apiService: ApiService) {
    suspend fun getStoriyLocation(): StoriyResult<List<ListStoriyItem?>> {
        try {
            val response = apiService.getStoriyLocation()
            if (response.error == true) {
                return StoriyResult.Error("Error: ${response.message}")
            } else {
                val storyLocation = response.listStory
                return StoriyResult.Success(storyLocation ?: emptyList())
            }
        } catch (e: Exception) {
            Log.e(TAG, "getStoryLocation: ${e.message.toString()}")
            return StoriyResult.Error(e.message.toString())
        }
    }

    companion object {
        private const val TAG = "StoryLocationRepository"

        @Volatile
        private var instance: StoriyLocationRepository? = null

        fun getInstance(apiService: ApiService): StoriyLocationRepository =
            instance ?: synchronized(this) {
                instance ?: StoriyLocationRepository(apiService)
            }.also { instance = it }
    }
}