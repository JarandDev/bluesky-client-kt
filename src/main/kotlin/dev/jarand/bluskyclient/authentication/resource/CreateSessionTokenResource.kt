package dev.jarand.bluskyclient.authentication.resource

data class CreateSessionTokenResource(
    val did: String,
    val handle: String,
    val email: String,
    val accessJwt: String,
    val refreshJwt: String
)
