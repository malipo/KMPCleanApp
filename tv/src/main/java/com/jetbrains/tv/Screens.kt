package com.jetbrains.tv

import com.jetbrains.tv.presentation.DetailsScreenArgs


enum class Screens(val args: List<String> = emptyList()) {
    Home,
    Details(listOf(DetailsScreenArgs.movieId)),
    Favorites,
    Settings;

    operator fun invoke(): String {
        val argList = StringBuilder()
        args.forEach { arg ->
            argList.append("/{$arg}")
        }
        return name + argList.toString()
    }


    fun withArgs(vararg args: Any): String {
        val destination = StringBuilder()


        if (args.size == this.args.size) {
            this.args.forEachIndexed { index, _ ->
                destination.append("/${args[index]}")
            }
        } else {
            throw IllegalArgumentException("Argument count does not match the number of required arguments for ${this.name}.")
        }

        return name + destination.toString()
    }
}
