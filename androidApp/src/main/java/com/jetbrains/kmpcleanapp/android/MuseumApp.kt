package com.jetbrains.kmpcleanapp.android

import android.app.Application
import com.jetbrains.museumlist.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
