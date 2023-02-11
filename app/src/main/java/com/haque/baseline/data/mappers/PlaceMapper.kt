package com.haque.baseline.data.mappers

import com.haque.baseline.data.model.PlaceData
import com.haque.baseline.data.source.source.dto.PlaceDTO

fun PlaceDTO.toPlace(): PlaceData {
    return PlaceData(
        this.city,
        this.latitude,
        this.longitude,
        this.country,
        this.state
    )
}