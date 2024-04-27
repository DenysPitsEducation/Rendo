package com.rendo.core.utils

import kotlin.math.roundToInt

fun Double.formatPrice(): String {
    val separator = ","
    val integerDigits = this.toInt()
    val formattedIntegerPart = integerDigits.formatWithSpaces()
    val formattedFloatPart = when (val floatDigits = ((this - integerDigits) * 100f).roundToInt()) {
        0 -> ""
        in 1..9 -> separator + "0" + floatDigits
        else -> separator + floatDigits
    }

    return formattedIntegerPart + formattedFloatPart
}

fun Float.formatPrice() = toDouble().formatPrice()

fun String.formatPrice() = toDoubleOrNullWithCleanup()?.formatPrice().orEmpty()

fun Double.formatPrice(currency: String?) = formatPrice() + currency?.let { " $it" }.orEmpty()

private fun Int.formatWithSpaces(): String = toString()
    .reversed()
    .chunked(3)
    .joinToString(separator = " ")
    .reversed()

fun String.toDoubleOrNullWithCleanup(): Double? {
    val cleanString = replace(Regex("[^\\d.,]"), "").replace(',', '.')
    return cleanString.toDoubleOrNull()
}
