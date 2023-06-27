package com.ayberk.valorantapp.models.competitive

data class Data(
    val assetObjectName: String,
    val assetPath: String,
    val tiers: List<Tier>,
    val uuid: String
)