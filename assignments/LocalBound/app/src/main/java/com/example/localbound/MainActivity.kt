package com.example.localbound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.postDelayed
import com.example.localbound.databinding.ActivityMainBinding
import kotlin.text.*

class MainActivity : AppCompatActivity() {

    // DO IT one by one! Step by Step!!!!

    private var stopwatchService: StopwatchService? = null
    private var isBound = false
    private lateinit var binding: ActivityMainBinding

    // create ServiceConnection (manages the connection to the service)
    private val connection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as StopwatchService.StopwatchBinder
            stopwatchService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    // next: onCreate method to set the button activities!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize binding objects
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{
            stopwatchService?.starTime()
            updateElapsedTime()
        }
        binding.pauseButton.setOnClickListener{
            stopwatchService?.pauseTime()
        }
        binding.resetButton.setOnClickListener{
            stopwatchService?.resetTime()
        }
    }
    private fun updateElapsedTime() {
        binding.textView.postDelayed(
            {
                if (isBound && stopwatchService != null) {
                    // Retrieve the elapsed time from the service
                    val elapsedTime = stopwatchService?.getElapsedTime() ?: 0L
                    // Calculate hours, minutes, and seconds from elapsed time
                    val seconds = (elapsedTime / 1000) % 60
                    val minutes = (elapsedTime / (1000 * 60)) % 60
                    val hours = (elapsedTime / (1000 * 60 * 60)) % 24
                    // Update the UI with the formatted time
                    binding.textView.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                }
                // Re-run updateElapsedTime every second
                updateElapsedTime()
            }, 1000)
        )
    }

    override fun onStart() {
        super.onStart()
        //bind to the service
        Intent(this, StopwatchService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if(isBound){
            unbindService(connection)
            isBound = false
        }
    }
}
