package com.jetbrains.kmpcleanapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = getPeopleDatabase(applicationContext).peopleDao()
        setContent {
            App(dao)
        }
        setContent {
            MyApplicationTheme {
                MainScreenRoute()
            }
        }
    }
}
