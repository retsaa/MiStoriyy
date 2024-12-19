package com.dicoding.mistoriyy.storiyy

import com.dicoding.mistoriyy.data.di.StoriyResult
import com.dicoding.mistoriyy.retrofit.ApiService


class StoriyRepository private constructor(private val apiService: ApiService) {
    suspend fun getStoriy(): StoriyResult<List<ListStoriyItem?>> {
        try {
            val response = apiService.getStories()
            if (response.error == true) {
                return StoriyResult.Error("Error: ${response.message}")
            } else {
                val story = response.listStory ?: emptyList()
                return StoriyResult.Success(story)
            }
        } catch (e: Exception) {
            return StoriyResult.Error(e.message.toString())
        }
    }

    companion object {

        @Volatile
        private var instance: StoriyRepository? = null

        fun getInstance(
            apiService: ApiService
        ): StoriyRepository =
            instance ?: synchronized(this) {
                instance ?: StoriyRepository(apiService)
            }.also { instance = it }
    }
}