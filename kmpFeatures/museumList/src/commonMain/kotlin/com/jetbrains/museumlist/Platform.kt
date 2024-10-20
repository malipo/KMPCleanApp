package com.jetbrains.museumlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform