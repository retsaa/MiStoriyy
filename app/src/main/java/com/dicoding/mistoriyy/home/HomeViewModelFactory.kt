package com.dicoding.mistoriyy.home

import android.content.Context
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.Injection
import com.dicoding.mistoriyy.storiyy.StoriyRepository

class HomeViewModelFactory (private val storiyRepository: StoriyRepository) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(storiyRepository) as T
        }
        throw IllegalArgumentException("viewmodel class tidak ditemukan" + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: HomeViewModelFactory? = null

        fun getInstance(
            context: Context
        ): HomeViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: HomeViewModelFactory(
                    Injection.sutoriRepository(context)
                )
            }.also { instance = it }
    }
}