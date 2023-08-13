package dev.jarand.bluskyclient.feed.resource

data class AuthorResource(
    val did: String,
    val handle: String,
    val displayName: String
    // TODO: viewer : Any
    // TODO: labels : List
)
