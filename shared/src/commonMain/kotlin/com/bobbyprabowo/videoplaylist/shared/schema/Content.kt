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
) {

    companion object {

        private fun fromEntity(content: com.bobbyprabowo.videoplaylist.db.Content): Content {
            return Content(
                title = content.title ?: "",
                presenterName = content.presenterName ?: "",
                description = content.description ?: "",
                thumbnailUrl = content.thumbnailUrl ?: "",
                videoUrl = content.videoUrl ?: "",
                videoDuration = content.videoDuration?.toInt() ?: 0
            )
        }

        fun fromEntities(contents: List<com.bobbyprabowo.videoplaylist.db.Content>): List<Content> {
            return contents.map {
                fromEntity(it)
            }
        }
    }

    fun toEntity(): com.bobbyprabowo.videoplaylist.db.Content {
        return com.bobbyprabowo.videoplaylist.db.Content(
            id = 0,
            title = title,
            presenterName = presenterName,
            description = description,
            thumbnailUrl = thumbnailUrl,
            videoUrl = videoUrl,
            videoDuration = videoDuration.toLong()
        )
    }
}
