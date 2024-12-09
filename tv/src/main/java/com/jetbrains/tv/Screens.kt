package com.jetbrains.tv

import com.jetbrains.tv.presentation.DetailsScreenArgs


enum class Screens(val args: List<Any>? = null) {
    Home,
    Details(listOf(DetailsScreenArgs.movieId));


    operator fun invoke(): String {
        val argList = StringBuilder()
        args?.let { nnArgs ->
            nnArgs.forEach { arg -> argList.append("/{$arg}") }
        }
        return name + argList
    }

    fun withArgs(vararg args: Any): String {
        val destination = StringBuilder()
        args.forEach { arg -> destination.append("/$arg") }
        return name + destination
    }
}