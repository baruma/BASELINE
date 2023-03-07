package com.haque.baseline.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

sealed class StringToTime {
    // to be applied to dto objects.

    // Used to format hourly time to human readable.
    // Original hourly time format: 2023-03-07 T03:00
    fun formatToHumanReadableTime(args: Array<String>) {
        val zonedDateTime: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        println(zonedDateTime.format(formatter))
    }
}


