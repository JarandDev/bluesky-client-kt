package dev.jarand.bluskyclient.feed.resource

data class PostResource(
    val uri: String,
    val cid: String,
    val author: AuthorResource,
    val record: RecordResource,
    val replyCount: Int,
    val repostCount: Int,
    val likeCount: Int,
    val indexedAt: String
    // TODO: viewer : Any
    // TODO: labels : List
)
