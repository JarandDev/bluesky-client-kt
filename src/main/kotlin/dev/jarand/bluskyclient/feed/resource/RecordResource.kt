package dev.jarand.bluskyclient.feed.resource

data class RecordResource(
    val text: String,
    val type: String?,
    val langs: List<String>,
    val reply: ReplyReferenceResource?,
    val createdAt: String
)
