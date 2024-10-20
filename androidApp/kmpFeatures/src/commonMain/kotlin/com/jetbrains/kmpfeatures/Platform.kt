package com.jetbrains.kmpfeatures

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform