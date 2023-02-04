package com.haque.baseline.domain

import android.location.Location

interface LocationFinder {
    suspend fun getCurrentLocation(): Location?
}