package com.haque.baseline.data.model

/*
While the Android Locatoin class could be used, for the sake of future testing, I want the code to be
as independent of dependencies as possible.
 */
data class PlaceData (
    val name: String,
    private val lat: Long,
    private val lon: Long
)