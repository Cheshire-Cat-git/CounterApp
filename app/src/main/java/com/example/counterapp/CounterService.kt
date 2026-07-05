package com.example.counterapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterService: Service() {

    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun incrementCounter() {
        _counter.value++
    }

    fun decrementCounter() {
        _counter.value--
    }

    fun showValue() {
        Toast.makeText(
            this@CounterService,
            counter.value.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    inner class LocalBinder : Binder() {
        fun getService(): CounterService = this@CounterService
    }
}
