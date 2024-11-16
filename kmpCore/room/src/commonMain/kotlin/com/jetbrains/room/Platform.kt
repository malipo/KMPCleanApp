package com.jetbrains.room

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform