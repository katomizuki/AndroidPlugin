package com.example.mylibrary
import kotlinx.serialization.*

@Serializable
data class AreaList(
    val features: List<LanLon>
)
@Serializable
data class LanLon(
    val lat: Double,
    val lon: Double
)