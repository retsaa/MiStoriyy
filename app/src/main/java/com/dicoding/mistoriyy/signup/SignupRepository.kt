package com.dicoding.mistoriyy.signup

import com.dicoding.mistoriyy.retrofit.ApiService


class SignupRepository (private val apiService: ApiService) {
    suspend fun signupUser(username: String, email: String, password: String): SignupResponse {
        return apiService.signupUser(username, email, password)
    }

    companion object {

        @Volatile
        private var instance: SignupRepository? = null

        fun getInstance(
            apiService: ApiService
        ): SignupRepository =
            instance ?: synchronized(this) {
                instance ?: SignupRepository(apiService)
            }.also { instance = it }
    }
}