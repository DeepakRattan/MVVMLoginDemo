package com.android.mvvmlogindemo

import android.app.Application
import com.android.mvvmlogindemo.modules.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}