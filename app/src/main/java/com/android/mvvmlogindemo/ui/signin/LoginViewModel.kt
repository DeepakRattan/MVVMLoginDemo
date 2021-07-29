package com.android.mvvmlogindemo.ui.signin

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mvvmlogindemo.data.models.LoginResponse
import com.android.mvvmlogindemo.data.repos.AppRepository
import com.android.mvvmlogindemo.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private val appRepository: AppRepository) : ViewModel() {
    var userLiveData = MutableLiveData<String>()
    var passwordLiveData = MutableLiveData<String>()

    // LiveData for errors
    var userError = MutableLiveData<String>()
    var passwordError = MutableLiveData<String>()

    var isError = MutableLiveData<String>()
    var loginResponse = MutableLiveData<LoginResponse>()

    private var loginResult = MutableLiveData<String>()

    fun login() {
        performValidation()
        GlobalScope.launch {
            val result = appRepository.login(userLiveData.value, passwordLiveData.value)
            Log.d("test", "result: $result")
            when (result) {
                is Resource.Error -> isError.postValue(result.code)
                is Resource.Success -> loginResponse.postValue(result.value)
            }
        }
    }

    private fun performValidation() {
        userError.value = null
        passwordError.value = null

        if (TextUtils.isEmpty(userLiveData.value)) {
            userError.value = "Empty field. Please enter a valid email-Id"
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userLiveData.value).matches()) {
            userError.value = "Invalid. Please enter a valid email-Id"
            return
        }
        if (TextUtils.isEmpty(passwordLiveData.value)) {
            passwordError.value = "Empty field. Please enter valid password"
            return
        }
        if (passwordLiveData.value?.length ?: 0 < 6) {
            passwordError.value = "Weak password. Please enter a strong one"
            return
        }

        //loginResult.value = "Valid Credentials"
    }


}