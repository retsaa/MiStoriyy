package com.dicoding.mistoriyy.Detail

import com.dicoding.mistoriyy.data.di.StoriyResult
import com.dicoding.mistoriyy.retrofit.ApiService


class DetailStoriyRepository private constructor(
    private val apiService: ApiService
) {
    suspend fun detailSutori(storyId: String): StoriyResult<Story?> {
        try {
            val response = apiService.getDetailStories(storyId)
            if (response.error == true) {
                return StoriyResult.Error("Error: ${response.message}")
            } else {
                val storyDetail = response.story
                return StoriyResult.Success(storyDetail)
            }
        } catch (e: Exception) {
            return StoriyResult.Error(e.message.toString())
        }
    }

    companion object {

        @Volatile
        private var instance: DetailStoriyRepository? = null

        fun getInstance(
            apiService: ApiService
        ): DetailStoriyRepository =
            instance ?: synchronized(this) {
                instance ?: DetailStoriyRepository(apiService)
            }.also { instance = it }
    }
}