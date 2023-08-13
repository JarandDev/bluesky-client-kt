package dev.jarand.bluskyclient.domain

data class User(
    val did: String,
    val handle: String,
    val email: String,
    val accessJwt: String,
    val refreshJwt: String
)
