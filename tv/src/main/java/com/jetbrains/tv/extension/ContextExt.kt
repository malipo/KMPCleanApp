package com.jetbrains.tv.extension

import android.content.Context

fun Context.getDrawableIdByName(drawableName: String): Int =
    resources.getIdentifier(drawableName, "drawable", packageName)