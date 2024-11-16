package com.jetbrains.room.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersonEntity::class],
    version = 1
)
abstract class PeopleDatabase: RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

}