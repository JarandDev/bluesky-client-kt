package dev.jarand.bluskyclient.util

import java.net.URI
import java.net.http.HttpRequest

fun createGETRequest(baseUrl: String, endpoint: String, actor: String, accessJwt: String): HttpRequest {
    return HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("$baseUrl/$endpoint?actor=$actor"))
        .header("Authorization", "Bearer $accessJwt")
        .build()
}


fun createPOSTRequest(baseUrl: String, endpoint: String, body: String): HttpRequest {
    return HttpRequest.newBuilder()
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .uri(URI.create("$baseUrl/$endpoint"))
        .header("Content-Type", "application/json")
        .build()
}
