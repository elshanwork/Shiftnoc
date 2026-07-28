package com.elshan.shiftnoc.util

import android.content.Context
import android.content.res.Configuration
import java.time.Month
import java.time.Year
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

object LocaleManager {
    fun setLocale(context: Context, language: String = "en"):Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }
}

fun updateLocale(context: Context, selectedLocale: String) {
    val updatedContext = LocaleManager.setLocale(context, selectedLocale)

    context.resources.updateConfiguration(
        updatedContext.resources.configuration,
        updatedContext.resources.displayMetrics
    )
}



fun YearMonth.displayText(short: Boolean = true): String {
    return if (this.year == Year.now().value) {
        this.month.displayText(short = short)
    } else {
        "${this.month.displayText(short = short)} ${this.year}"
    }
}

fun Month.displayText(short: Boolean = true): String {
    val style = if (short) TextStyle.FULL else TextStyle.SHORT
    return getDisplayName(style, Locale.getDefault())
}


fun truncateDecimalPlaces(str: String): String {
    val indexOfDot = str.indexOf(".")
    if (indexOfDot < 0) {
        return str // No decimal point found
    }


    if (indexOfDot + 1 >= str.length) {
        return str
    }

    val truncatedDecimalPart = str.substring(indexOfDot + 1, minOf(indexOfDot + 3, str.length))

    return str.substring(0, indexOfDot + 1) + truncatedDecimalPart
}

