package com.rendo.core.utils

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

fun LocalDate.Companion.fromEpochMilliseconds(millis: Long, timeZone: TimeZone = TimeZone.UTC): LocalDate {
    return Instant.fromEpochMilliseconds(millis).toLocalDateTime(timeZone).date
}

operator fun ClosedRange<LocalDate>.iterator() = object: Iterator<LocalDate> {
    private var currentDate = start

    override fun hasNext() = currentDate <= endInclusive

    override fun next(): LocalDate {
        val next = currentDate
        currentDate = currentDate.plus(1, DateTimeUnit.DAY)
        return next
    }
}
