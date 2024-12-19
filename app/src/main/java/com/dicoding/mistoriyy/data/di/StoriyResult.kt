package com.dicoding.mistoriyy.data.di

sealed class StoriyResult<out R> private constructor() {
    data class Success<out T>(val data: T) : StoriyResult<T>()
    data class Error(val error: String) : StoriyResult<Nothing>()
    object Loading : StoriyResult<Nothing>()
}