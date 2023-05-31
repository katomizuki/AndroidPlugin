package com.example.mylibrary
import kotlinx.serialization.*

@Serializable
data class Root(
    val type: String,
    val features: List<Feature>
)
@Serializable
data class Feature(
    val type: String,
    val geometry: Geometry
)
@Serializable
data class Geometry(
    val type: String,
    val properties: Properties,
    val coordinates: List<List<List<Double>>>
)
@Serializable
data class Properties(
    @SerialName("location_type")
    val locationType: String,
    @SerialName("localizability_quality")
    val localizabilityQuality: String,
    @SerialName("location_target_identifiers")
    val locationTargetIdentifiers: List<String>
)