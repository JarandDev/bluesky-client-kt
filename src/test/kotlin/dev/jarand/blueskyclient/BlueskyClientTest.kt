package dev.jarand.blueskyclient

import dev.jarand.bluskyclient.BlueskyClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class BlueskyClientTest {

    @Test
    fun `should load base url`() {
        val blueskyClient = BlueskyClient()

        val baseUrl = blueskyClient.getBaseUrl()

        assertThat(baseUrl).isEqualTo("https://bsky.social/xrpc")
    }

    @Test
    @Disabled("For manual testing")
    fun `should test the application manually`() {
        val blueskyClient = BlueskyClient()

        val user = blueskyClient.authenticate() ?: return println("Failed to authenticate")
        val feed = blueskyClient.getAuthorFeed(user = user) ?: return println("Failed to get author feed")
        feed.feed.forEach {
            println("${it.post.record.createdAt} | ${it.post.author.handle} | ${it.post.record.text}")
        }
        println()
    }
}
