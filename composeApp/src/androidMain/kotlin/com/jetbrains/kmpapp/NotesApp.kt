package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.di.androidModule
import com.jetbrains.kmpapp.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//import com.jetbrains.kmpapp.di.initKoin

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NotesApp)
            modules(commonModule, androidModule)
        }
    }
}
