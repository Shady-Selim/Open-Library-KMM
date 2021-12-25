package com.shady.openlibrary.network

import com.shady.openlibrary.entities.LibraryQuery
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class ApiImpl: Api  {
    val baseUrl = "http://openlibrary.org/search.json"

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
                coerceInputValues = true
            })
        }
    }

    override suspend fun getDocuments(queryType: String, queryString: String): LibraryQuery {
        return httpClient.get("${baseUrl}?${queryType}=${queryString}")
    }
}