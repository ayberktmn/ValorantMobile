package com.ayberk.valorantapp.models.Agends

data class DamageRange(
    val bodyDamage: Int,
    val headDamage: Double,
    val legDamage: Double,
    val rangeEndMeters: Int,
    val rangeStartMeters: Int
)