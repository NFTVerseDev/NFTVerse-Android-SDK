package com.himanskdevstuff.gonftverse


import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.himanskdevstuff.gnv_android_sdk.presentation.landing.GoNftVerse
import com.himanskdevstuff.gonftverse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startSDK.setOnClickListener {
            val goNftSDK = GoNftVerse(this)
            goNftSDK.setTheme(R.style.Theme_GoNftVerse)
            goNftSDK.startSDK()
        }
    }

}