package com.example.demorequest.network

import kotlinx.serialization.Serializable

@Serializable
data class InfoList(
    val status: String,
    val comment: String? = null,
    val result: List<UserData>
)