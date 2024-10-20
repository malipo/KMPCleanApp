package com.jetbrains.kmpcleanapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform