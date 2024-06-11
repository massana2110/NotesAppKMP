package com.jetbrains.kmpapp

import androidx.compose.ui.window.ComposeUIViewController
import com.jetbrains.kmpapp.di.commonModule
import com.jetbrains.kmpapp.di.iOSModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    App()
}

fun initKoin() = startKoin {
    modules(commonModule, iOSModule)
}
