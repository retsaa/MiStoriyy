package com.dicoding.mistoriyy.data.di


import android.content.Context
import com.dicoding.mistoriyy.Detail.DetailStoriyRepository
import com.dicoding.mistoriyy.login.LoginRepository
import com.dicoding.mistoriyy.pref.UserPreference
import com.dicoding.mistoriyy.pref.dataStore
import com.dicoding.mistoriyy.retrofit.ApiConfig
import com.dicoding.mistoriyy.signup.SignupRepository
import com.dicoding.mistoriyy.storiyy.StoriyLocationRepository
import com.dicoding.mistoriyy.storiyy.addStoriy.AddStoriyRepository
import com.dicoding.mistoriyy.storiyy.StoriyRepository
import kotlinx.coroutines.runBlocking

object Injection {

    fun registerRepository(context: Context): SignupRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUserToken() }
        val apiService = ApiConfig.getApiService(user)
        return SignupRepository.getInstance(apiService)
    }

    fun loginRepository(context: Context): LoginRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUserToken() }
        val apiService = ApiConfig.getApiService(user)
        return LoginRepository.getInstance(apiService)
    }

    fun AddStoriyRepository(context: Context): AddStoriyRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUserToken() }
        val apiService = ApiConfig.getApiService(user)
        return AddStoriyRepository.getInstance(apiService)
    }

    fun sutoriRepository(context: Context): StoriyRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUserToken() }
        val apiService = ApiConfig.getApiService(user)
        return StoriyRepository.getInstance(apiService)
    }

    fun detailSutoriRepository(context: Context): DetailStoriyRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUserToken() }
        val apiService = ApiConfig.getApiService(user)
        return DetailStoriyRepository.getInstance(apiService)
    }

    fun userPreference(context: Context): UserPreference {
        return UserPreference.getInstance(context.dataStore)
    }

    fun storiyLocationRepository(context: Context): StoriyLocationRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getUserToken() }
        val apiService = ApiConfig.getApiService(user)
        return StoriyLocationRepository.getInstance(apiService)
    }
}