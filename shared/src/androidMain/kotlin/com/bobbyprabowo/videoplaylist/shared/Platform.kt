package com.bobbyprabowo.videoplaylist.shared

import android.content.Context
import com.bobbyprabowo.videoplaylist.db.VideoPlaylistDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual class Platform(context: Context) {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    actual val database: VideoPlaylistDatabase = VideoPlaylistDatabase.invoke(
        AndroidSqliteDriver(
            VideoPlaylistDatabase.Schema,
            context,
            "videoplaylist.db"
        )
    )
}