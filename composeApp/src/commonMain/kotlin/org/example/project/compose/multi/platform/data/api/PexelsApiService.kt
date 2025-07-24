package org.example.project.compose.multi.platform.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import kotlinx.coroutines.TimeoutCancellationException
import org.example.project.compose.multi.platform.domain.models.PexelsResponse

class PexelsApiService(
    private val httpClient: HttpClient
) {

    companion object {
        private const val BASE_URL = "https://api.pexels.com/v1"
        private const val API_KEY = "Add api key here."
    }

    suspend fun getCuratedPhotos(page: Int, perPage: Int): PexelsResponse? {
        return try {
            httpClient.get("$BASE_URL/curated") {
                header("Authorization", API_KEY)
                parameter("page", page)
                parameter("per_page", perPage)
                timeout {
                    requestTimeoutMillis = 30_000
                    connectTimeoutMillis = 15_000
                    socketTimeoutMillis = 30_000
                }
            }.body()
        } catch (e: ResponseException) {
            print("HTTP error: ${e.response.status} - ${e.message}")
            null
        } catch (e: TimeoutCancellationException) {
            print("Request timed out: ${e.message}")
            null
        } catch (e: Exception) {
            print("Unexpected error: ${e.message}")
            null
        }
    }

    suspend fun searchPhotos(query: String, page: Int, perPage: Int): PexelsResponse {
        return httpClient.get("$BASE_URL/search") {
            header("Authorization", API_KEY)
            parameter("query", query)
            parameter("page", page)
            parameter("per_page", perPage)
            timeout {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 15_000
                socketTimeoutMillis = 30_000
            }
        }.body()
    }
}