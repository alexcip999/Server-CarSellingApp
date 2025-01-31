package com.example.models

data class Car(
    val id: Int?,
    // common id from the user who upload this car
    val idUser: Int,
    // details for card component
    val year: String,
    val km: String,
    val combustible: CombustibleType,
    val power: String,
    val capacity: String,
    val price: String,
    // more details
    val description: String,
    val mark: String,
    val model: String,
    val color: String,
    val seller: String,

    val principalImageUri: String,         // URI for the principal image
    val secondaryImageUris: List<String>  // URIs for secondary images
)

enum class CombustibleType(private val displayName: String) {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid"),
    BENZINE("Benzine");

    override fun toString(): String = displayName
}
