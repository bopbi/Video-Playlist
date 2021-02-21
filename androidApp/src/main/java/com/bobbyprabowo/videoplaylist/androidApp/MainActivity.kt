package com.bobbyprabowo.videoplaylist.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bobbyprabowo.videoplaylist.shared.Greeting
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bobbyprabowo.videoplaylist.shared.datasource.remote.VideoApi
import com.bobbyprabowo.videoplaylist.shared.repository.impl.ContentRepositoryImpl
import com.bobbyprabowo.videoplaylist.shared.usecase.impl.FetchPlaylistImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.subscribeOn

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        val contentRepository = ContentRepositoryImpl(VideoApi)
        val fetchPlaylist = FetchPlaylistImpl(contentRepository)
        lifecycleScope.launchWhenCreated {
            fetchPlaylist.execute().flowOn(Dispatchers.IO).collect()
        }
    }
}
