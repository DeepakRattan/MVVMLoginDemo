package com.android.mvvmlogindemo.data.apis

import com.android.mvvmlogindemo.data.models.LoginResponse
import com.android.mvvmlogindemo.utils.Constant
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST(Constant.LOGIN)
    suspend fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ) : Response<LoginResponse>
}