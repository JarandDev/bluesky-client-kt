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
    fun `should authenticate`() {
        val blueskyClient = BlueskyClient()

        blueskyClient.authenticate()
    }
}
