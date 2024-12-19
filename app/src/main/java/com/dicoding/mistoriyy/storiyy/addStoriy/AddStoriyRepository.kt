package com.dicoding.mistoriyy.storiyy.addStoriy


import com.dicoding.mistoriyy.retrofit.ApiService
import okhttp3.*

class AddStoriyRepository (private val apiService: ApiService) {
    suspend fun addStoriy(file: MultipartBody.Part, description: RequestBody): AddStoriyResponse {
        return apiService.addStutori(file, description)
    }

    companion object {
        @Volatile
        private var instance: AddStoriyRepository? = null

        fun getInstance(
            apiService: ApiService
        ): AddStoriyRepository =
            instance ?: synchronized(this) {
                instance ?: AddStoriyRepository(apiService)
            }.also { instance = it }
    }
}