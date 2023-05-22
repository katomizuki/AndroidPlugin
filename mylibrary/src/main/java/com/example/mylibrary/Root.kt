package com.example.mylibrary
import kotlinx.serialization.*

data class Root(
    val type: String,
    val features: List<Feature>
)

data class Feature(
    val type: String,
    val geometry: Geometry
)

data class Geometry(
    val type: String,
    val properties: Properties,
    val coordinates: List<List<List<Double>>>
)

data class Properties(
    @SerialName("location_type")
    val locationType: String,
    @SerialName("localizability_quality")
    val localizabilityQuality: String,
    @SerialName("location_target_identifiers")
    val locationTargetIdentifiers: List<String>
)