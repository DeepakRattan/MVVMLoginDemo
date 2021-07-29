package com.android.mvvmlogindemo.data.repos

import com.android.mvvmlogindemo.data.apis.Api
import com.android.mvvmlogindemo.data.models.LoginResponse
import com.android.mvvmlogindemo.utils.Resource

class AppRepository(private val api: Api) {

    suspend fun login(username: String?, passwd: String?): Resource<LoginResponse?> {
        return try {
            val result = api.login(username, passwd)
            if (result.code() == 200 && result.body() != null) {
                Resource.Success(result.body())
            } else {
                Resource.Error("Invalid Credentials")
            }
        } catch (e: Exception) {
            Resource.Error("Unknown Error")
        }
    }
}