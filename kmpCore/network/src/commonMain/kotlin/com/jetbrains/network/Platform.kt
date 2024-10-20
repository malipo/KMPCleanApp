package com.jetbrains.network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform