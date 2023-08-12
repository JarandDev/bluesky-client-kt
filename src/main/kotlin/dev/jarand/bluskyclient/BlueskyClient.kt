package dev.jarand.bluskyclient

import dev.jarand.bluskyclient.config.Properties

class BlueskyClient {

    private val properties = Properties()

    fun getBaseUrl(): String {
        return properties.getString("bluesky.baseUrl")
    }
}
