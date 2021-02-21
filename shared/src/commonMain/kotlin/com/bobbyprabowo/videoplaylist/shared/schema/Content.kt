package com.bobbyprabowo.videoplaylist.shared.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Content(
    @SerialName("title")
    val title: String,
    @SerialName("presenter_name")
    val presenterName: String,
    @SerialName("description")
    val description: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("video_url")
    val videoUrl: String,
    @SerialName("video_duration")
    val videoDuration: Int
)
