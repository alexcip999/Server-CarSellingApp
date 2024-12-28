package com.example.service

import com.example.models.CombustibleType

data class UploadCarParams(
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
    val color: String,
    val seller: String,

    val principalImageUri: String,         // URI for the principal image
    val secondaryImageUris: List<String>  // URIs for secondary images
)
