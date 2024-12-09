package com.jetbrains.tv

import android.app.Application
import com.jetbrains.tv.di.dataModule
import com.jetbrains.tv.di.initKoin
import com.jetbrains.tv.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class TVApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TVApplication)
            modules(
                dataModule,
                viewModelModule,
            )
        }
    }
}
