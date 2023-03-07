package com.haque.baseline.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object ReadableDateTimeHelper {
    // to be applied to dto objects.

    // Used to format hourly time to human readable.
    // Original hourly time format: 2023-03-07 T03:00 or yyyy-MM-dd'T'HH:mm
    fun formatToReadableTime(nonReadableTime: List<String>): String {
        val zonedDateTime: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))

        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        println(zonedDateTime.format(formatter))

        return zonedDateTime.format(formatter)
    }

    fun formatToReadableDate() {}
}
