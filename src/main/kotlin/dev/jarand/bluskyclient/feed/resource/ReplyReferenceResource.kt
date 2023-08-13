package dev.jarand.bluskyclient.feed.resource

data class ReplyReferenceResource(
    val root: RootReferenceResource,
    val parent: ParentReferenceResource
)
