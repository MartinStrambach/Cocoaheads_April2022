package com.example.multiplatform.android

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multiplatform.Greeting
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.multiplatform.TimeRepository
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        lifecycleScope.launchWhenResumed {
            TimeRepository().seconds().collect {
                tv.text = "millis: ${it}"
            }
        }
    }
}
