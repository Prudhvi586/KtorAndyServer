package com.pru.ktorandyserver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.pru.ktorandyserver.databinding.ActivityMainBinding
import com.pru.ktorandyserver.server.ServerUtils
import com.pru.ktorandyserver.ui.MyTextView
import com.pru.ktorandyserver.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val baseUrl = String.format(
            "http://%s:%d", NetworkUtils.getLocalIpAddress(),
            NetworkUtils.port
        )
        var tv = getTextView(baseUrl)
        binding.llParent.addView(tv)
        enumValues<ServerUtils.ApiEndPoints>().forEach {
            tv = getTextView(it.name.plus(" - ${it.endpoint}"))
            binding.llParent.addView(tv)
        }
        lifecycleScope.launch(Dispatchers.IO) {
            apiServer.startServer()
        }
    }

    private fun getTextView(value: String) = MyTextView(value, this)

    override fun onDestroy() {
        super.onDestroy()
        apiServer.stopServer()
    }
}