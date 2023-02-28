package com.haque.baseline.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
While the Android Location
 class could be used, for the sake of future testing, I want the code to be
as independent of dependencies as possible.
 */
data class PlaceData (
    val city: String,
    val lat: Float,
    val lon: Float,
    val country: String,
    val state: String
)