package com.htnguyen.gplxapp

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.htnguyen.gplxapp.base.utils.SharePreference
import com.htnguyen.gplxapp.di.adapterModule
import com.htnguyen.gplxapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MainApp : Application() {
    companion object {
        lateinit var instance: MainApp
    }

    private var currentActivity: Activity? = null
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApp)
            modules(listOf(
                viewModelModule,
                adapterModule
            ))

        }
        instance = this

        if (SharePreference.isModeLight) {
            setDayModeOrSth()
        } else {
            setNightMode()
        }
    }

    fun setCurrentActivity(currentActivity: Activity) {
        this.currentActivity = currentActivity
    }

    fun getCurrentActivity(): Activity? {
        return currentActivity
    }

    fun setNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun setDayModeOrSth() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun adjustFontScale(scale: Float) {
        /*configuration?.let {
            it.fontScale = scale
            val metrics: DisplayMetrics = resources.displayMetrics
            val wm: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density

            baseContext.applicationContext.createConfigurationContext(it)
            baseContext.resources.displayMetrics.setTo(metrics)
        }*/

        val configuration = resources.configuration
        configuration.fontScale = scale


        val metrics = DisplayMetrics()
        val wm: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }
}