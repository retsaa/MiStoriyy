package com.dicoding.mistoriyy.signup


import android.app.Application
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.Injection


class SignupViewModelFactory private constructor(
    private val application: Application,
    private val registerRepository: SignupRepository
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(application, registerRepository) as T
        }
        throw IllegalArgumentException("viewmodel class tidak ditemukan" + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: SignupViewModelFactory? = null

        fun getInstance(application: Application): SignupViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: SignupViewModelFactory(
                    application,
                    Injection.registerRepository(application)
                )
            }.also { instance = it }
    }

}