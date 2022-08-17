package com.example.caloriecounter.utils

sealed class Resource<T>(var data: T?, var message: String = "") {
    data class Success<T>(val dataList: T) : Resource<T>(data = dataList)
    data class Error<T>(val errorMessage: String) : Resource<T>(message = errorMessage, data = null)
    data class Loading<T>(val isLoading: Boolean) : Resource<T>(data = null)
}