package com.dicoding.mistoriyy.storiyy.addStoriy



import android.content.Context
import androidx.lifecycle.*
import com.dicoding.mistoriyy.data.di.Injection

class AddStoriyViewModelFactory (
    private val addStoryRepository: AddStoriyRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddStoriyViewModel::class.java)) {
            return AddStoriyViewModel(addStoryRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: AddStoriyViewModelFactory? = null

        fun getInstance(context: Context): AddStoriyViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: AddStoriyViewModelFactory(
                    Injection.AddStoriyRepository(context)
                )
            }.also { instance = it }
    }
}