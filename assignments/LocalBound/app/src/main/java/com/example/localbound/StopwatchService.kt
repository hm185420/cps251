package com.example.localbound

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.ConditionVariable
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.SystemClock
import java.util.Timer
import java.util.TimerTask



class StopwatchService : Service() {

    // DO IT one by one!!
    // add the service to the Manifest.xml

    // set the binder variables
    private val binder = StopwatchBinder()

    // set running variables for true or false to distinguish start or stop
    // make it both false as default
    private var running = false
    private var paused = false

    private var startStopwatch : Long = 0
    private var pauseStopwatch: Long = 0
    private var elapsedTime: Long = 0

    // post Runnable task to the main thread
    private val handler = Handler(Looper.getMainLooper())

    // Inner class: "getService" to return the current instance of StopwatchService
    inner class StopwatchBinder : Binder() {
        fun getService(): StopwatchService = this@StopwatchService
    }


    override fun onBind(intent: Intent?): IBinder? {
       return binder
    }

    // 1. start button function
    fun starTime(){
        running = true
        startStopwatch = SystemClock.elapsedRealtime()
        // create "updateRunnable"
        updateRunnable.run()
    }

    // 2. pause button function
    fun pauseTime(){
        running = false
        paused = true
        pauseStopwatch = elapsedTime
    }

    // 3. reset button function
    fun resetTime(){
        running = false
        paused = false
        elapsedTime = 0
    }

    // use the SystemClock.elapsedRealtime to return the time as the system was booted
    private val updateRunnable = object : Runnable {
        override fun run() {
            val timeNow = SystemClock.elapsedRealtime()
            if (running && paused){
                // calculate the elapsed time : paused time + cuurrentTime - startedTime
                elapsedTime = pauseStopwatch + timeNow - startStopwatch
                // use postDelayed method: should be run in the future
                // Include two parameters: Runnable + Long
                handler.postDelayed(this,100)
            }
            else if (running && !paused){
               elapsedTime = timeNow - startStopwatch
                handler.postDelayed(this,100)
            }
        }
    }
    fun getElapsedTime() : Long{
        return elapsedTime
    }


}