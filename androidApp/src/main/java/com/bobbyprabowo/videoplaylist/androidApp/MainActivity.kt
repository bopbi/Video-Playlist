package com.bobbyprabowo.videoplaylist.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bobbyprabowo.videoplaylist.shared.Platform
import com.bobbyprabowo.videoplaylist.shared.datasource.remote.VideoApi
import com.bobbyprabowo.videoplaylist.shared.repository.impl.ContentRepositoryImpl
import com.bobbyprabowo.videoplaylist.shared.usecase.impl.InitialFetchPlaylistImpl
import com.bobbyprabowo.videoplaylist.shared.usecase.impl.InitialLoadPlaylistImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

@FlowPreview
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platform = Platform(this)
        val tv: TextView = findViewById(R.id.text_view)
        tv.text = platform.platform

        val contentRepository = ContentRepositoryImpl(VideoApi, platform.database)
        val fetchPlaylist = InitialFetchPlaylistImpl(contentRepository)
        val loadPlaylist = InitialLoadPlaylistImpl(contentRepository)
        lifecycleScope.launchWhenCreated {
            loadPlaylist.execute().flowOn(Dispatchers.IO).collect{
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_LONG).show()
            }
            fetchPlaylist.execute().flowOn(Dispatchers.IO).collect{
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}
