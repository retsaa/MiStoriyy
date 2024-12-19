package com.dicoding.mistoriyy.setting


import android.content.Context
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.Injection
import com.dicoding.mistoriyy.pref.UserPreference


class SettingViewModelFactory (
    private val userPreference: UserPreference
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(userPreference) as T
        }
        throw IllegalArgumentException("viewmodel class tidak ditemukan" + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: SettingViewModelFactory? = null

        fun getInstance(context: Context)
                : SettingViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: SettingViewModelFactory(
                    Injection.userPreference(context)
                )
            }.also { instance = it }
    }
}