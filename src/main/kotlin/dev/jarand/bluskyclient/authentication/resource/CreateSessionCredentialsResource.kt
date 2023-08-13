package dev.jarand.bluskyclient.authentication.resource

data class CreateSessionCredentialsResource(
    val identifier: String,
    val password: String
)
