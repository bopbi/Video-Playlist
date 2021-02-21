package com.bobbyprabowo.videoplaylist.shared.datasource.remote

import com.bobbyprabowo.videoplaylist.shared.schema.Content
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object VideoApi {

    private const val playlistUrl = "https://quipper.github.io/native-technical-exam/playlist.json"

    private val nonStrictJson = Json {
        isLenient = true;
        ignoreUnknownKeys = true
    }

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(nonStrictJson)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    fun fetchPlaylist(): Flow<List<Content>> {
        return flow {
            emit(client.get(playlistUrl))
        }
    }
}