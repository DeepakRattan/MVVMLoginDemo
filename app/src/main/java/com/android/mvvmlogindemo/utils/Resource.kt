package com.android.mvvmlogindemo.utils

sealed class Resource<out T> {
    data class Success<T>(val value: T):Resource<T>()
    data class Error<T>(val code: String) : Resource<T>()
}