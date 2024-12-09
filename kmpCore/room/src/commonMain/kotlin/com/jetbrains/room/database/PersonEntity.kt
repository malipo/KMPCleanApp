package com.jetbrains.room.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonEntity(
    val name: String,
    @PrimaryKey val id: String,
)
