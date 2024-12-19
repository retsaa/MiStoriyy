package com.dicoding.mistoriyy.login


import android.app.Application
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.Injection
import com.dicoding.mistoriyy.pref.UserPreference

class LoginViewModelFactory private constructor(
    private val application: Application,
    private val loginRepository: LoginRepository,
    private val userPreference: UserPreference
) : ViewModelProvider.AndroidViewModelFactory(application) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(application, loginRepository, userPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class:" + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: LoginViewModelFactory? = null
        fun getInstance(
            application: Application,
            userPreference: UserPreference
        ): LoginViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: LoginViewModelFactory(
                    application,
                    Injection.loginRepository(application),
                    userPreference
                )
            }.also { instance = it }
    }
}