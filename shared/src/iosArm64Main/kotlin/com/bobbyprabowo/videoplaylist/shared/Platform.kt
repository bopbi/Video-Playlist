package com.bobbyprabowo.videoplaylist.shared

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.bobbyprabowo.videoplaylist.db.VideoPlaylistDatabase
import platform.UIKit.UIDevice

actual class Platform {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val database: VideoPlaylistDatabase = VideoPlaylistDatabase.invoke(NativeSqliteDriver(VideoPlaylistDatabase.Schema, "videoplaylist.db"))
}