package com.example.demorequest.network

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val avatar: String? = null,
    val contribution: Int,
    val firstName: String? = null,
    val friendOfCount: Int,
    val handle: String,
    val lastName: String? = null,
    val lastOnlineTimeSeconds: Int,
    val maxRank: String,
    val maxRating: Int,
    val organization: String? = null,
    val rank: String,
    val rating: Int,
    val registrationTimeSeconds: Int,
    val titlePhoto: String? = null
)