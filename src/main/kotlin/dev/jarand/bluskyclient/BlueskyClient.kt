package dev.jarand.bluskyclient

import com.fasterxml.jackson.module.kotlin.readValue
import dev.jarand.bluskyclient.config.Properties
import dev.jarand.bluskyclient.config.createObjectMapper
import dev.jarand.bluskyclient.user.domain.User
import dev.jarand.bluskyclient.user.domain.UserAssembler
import dev.jarand.bluskyclient.feed.resource.FeedResource
import dev.jarand.bluskyclient.authentication.resource.CreateSessionCredentialsResource
import dev.jarand.bluskyclient.authentication.resource.CreateSessionTokenResource
import dev.jarand.bluskyclient.util.createGETRequest
import dev.jarand.bluskyclient.util.createPOSTRequest
import org.slf4j.LoggerFactory
import java.net.http.HttpClient
import java.net.http.HttpResponse.BodyHandlers

class BlueskyClient {

    private val client = HttpClient.newHttpClient()
    private val objectMapper = createObjectMapper()
    private val properties = Properties()
    private val userAssembler = UserAssembler()

    fun getBaseUrl(): String {
        return properties.getString("bluesky.baseUrl")
    }

    fun authenticate(): User? {
        val createSessionCredentials = CreateSessionCredentialsResource(
            identifier = properties.getString("bluesky.user.handle"),
            password = properties.getString("bluesky.user.password")
        )
        logger.info("Authenticating user with handle as identifier: ${createSessionCredentials.identifier}")
        val request = createPOSTRequest(
            baseUrl = getBaseUrl(),
            endpoint = "com.atproto.server.createSession",
            body = objectMapper.writeValueAsString(createSessionCredentials)
        )
        val response = client.send(request, BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            logger.error("Failed to authenticate user with handle as identifier: ${createSessionCredentials.identifier} (HTTP Response code: ${response.statusCode()})")
            return null
        }
        val createSessionToken = objectMapper.readValue<CreateSessionTokenResource>(response.body())
        val user = userAssembler.assemble(createSessionToken)
        logger.info("Successfully authenticated user with handle as identifier: ${user.handle} (email: ${user.email}, did: ${user.did})")
        return user
    }

    fun getAuthorFeed(user: User): FeedResource? {
        logger.info("Getting author feed for user with handle: ${user.handle}")
        val request = createGETRequest(
            baseUrl = getBaseUrl(),
            endpoint = "app.bsky.feed.getAuthorFeed",
            actor = user.did,
            accessJwt = user.accessJwt
        )
        val response = client.send(request, BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            logger.error("Failed to get author feed for user with handle: ${user.handle} (HTTP Response code: ${response.statusCode()})")
            return null
        }
        val feedResource = objectMapper.readValue<FeedResource>(response.body())
        logger.info("Got author feed with '${feedResource.feed.size}' posts for user with handle: ${user.handle}")
        return feedResource
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BlueskyClient::class.java)
    }
}
