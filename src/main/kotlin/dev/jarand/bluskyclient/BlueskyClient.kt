package dev.jarand.bluskyclient

import com.fasterxml.jackson.module.kotlin.readValue
import dev.jarand.bluskyclient.config.Properties
import dev.jarand.bluskyclient.config.createObjectMapper
import dev.jarand.bluskyclient.resource.CreateSessionCredentialsResource
import dev.jarand.bluskyclient.resource.CreateSessionTokenResource
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers

class BlueskyClient {

    private val properties = Properties()
    private val objectMapper = createObjectMapper()

    fun getBaseUrl(): String {
        return properties.getString("bluesky.baseUrl")
    }

    fun authenticate() {
        val createSessionCredentials = CreateSessionCredentialsResource(
            identifier = properties.getString("bluesky.user.handle"),
            password = properties.getString("bluesky.user.password")
        )
        logger.info("Authenticating user with handle as identifier: ${createSessionCredentials.identifier}")
        val client = HttpClient.newHttpClient()
        val request = createPOST(endpoint = "com.atproto.server.createSession", body = createSessionCredentials)
        val response = client.send(request, BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            logger.error("Failed to authenticate user with handle as identifier: ${createSessionCredentials.identifier} (HTTP Response code: ${response.statusCode()})")
            return
        }
        val createSessionToken = objectMapper.readValue<CreateSessionTokenResource>(response.body())
        logger.info("Successfully authenticated user with handle as identifier: ${createSessionToken.handle} (email: ${createSessionToken.email}, did: ${createSessionToken.did})")
    }

    private fun createPOST(endpoint: String, body: Any): HttpRequest {
        return HttpRequest.newBuilder()
            .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
            .uri(URI.create("${getBaseUrl()}/$endpoint"))
            .header("Content-Type", "application/json")
            .build()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BlueskyClient::class.java)
    }
}
