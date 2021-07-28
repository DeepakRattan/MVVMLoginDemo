package com.android.mvvmlogindemo.modules

import com.android.mvvmlogindemo.data.apis.Api
import com.android.mvvmlogindemo.utils.constants.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Terminologies in Koin

/* 1. module : create module in Koin which would be used by Koin to provide all the dependencies.

   2. single : is used to create a singleton class
      i.e the Koin will return the same instance of the class when the dependency is needed.

   3. factory : will create a new instance each time it is injected.

   4. get() : is used in the constructor of a class to provide the required dependency.
      The get function is used to get the required dependency only if it has been specified in the module.
*/


var appModule = module {

    single {
        okHttpClient()
    }

    single {
        createWebService<Api>(get())
    }

}

fun okHttpClient(): OkHttpClient {

    return OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .addInterceptor(getHttpLoggingInterceptor())
        .build()
}

//  reified keyword is used to find the type of T in generic function
inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}


private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}