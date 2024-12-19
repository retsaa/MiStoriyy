package com.dicoding.mistoriyy.Detail


import android.content.Context
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.Injection

class DetailViewModelFactory (private val detailStoriyRepository: DetailStoriyRepository): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(detailStoriyRepository) as T
        }
        throw IllegalArgumentException("viewmodel class tidak ditemukan" + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: DetailViewModelFactory? = null

        fun getInstance(
            context: Context,
            detailStoriyRepository: DetailStoriyRepository
        ): DetailViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetailViewModelFactory(
                    Injection.detailSutoriRepository(context)
                )
            }.also { instance = it }
    }
}