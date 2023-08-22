package com.htnguyen.gplxapp

import android.app.Application
import com.htnguyen.gplxapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {
    companion object {
        lateinit var instance: MainApp
            private set
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(listOf(
                viewModelModule,
            ))

        }
        instance = this
    }
}