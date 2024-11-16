package com.jetbrains.room.di

import android.content.Context
import android.util.Log
import com.jetbrains.room.database.PeopleDao
import com.jetbrains.room.database.PeopleDatabase
import com.jetbrains.room.database.getPeopleDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val roomModule = module {
    single<PeopleDatabase> {
        Log.d("TAG_mali", "roomModule ")
        getPeopleDatabase(get<Context>())
    }
    single<PeopleDao> { get<PeopleDatabase>().peopleDao() }
}
