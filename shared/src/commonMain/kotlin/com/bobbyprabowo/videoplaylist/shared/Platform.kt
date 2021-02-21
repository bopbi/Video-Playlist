package com.bobbyprabowo.videoplaylist.shared

import com.bobbyprabowo.videoplaylist.db.VideoPlaylistDatabase

expect class Platform {
    val platform: String

    val database: VideoPlaylistDatabase
}