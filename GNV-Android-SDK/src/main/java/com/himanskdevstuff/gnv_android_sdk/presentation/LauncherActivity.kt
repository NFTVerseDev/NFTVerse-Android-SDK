package com.himanskdevstuff.gnv_android_sdk.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.himanskdevstuff.gnv_android_sdk.R
import com.himanskdevstuff.gnv_android_sdk.constants.Constants
import com.himanskdevstuff.gnv_android_sdk.presentation.util.StoreDetails

class LauncherActivity() : AppCompatActivity() {
    private lateinit var storeName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val yourCustomTheme = intent.getIntExtra(Constants.CUSTOM_THEME,0)
        setTheme(yourCustomTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        setItemValues()
    }

    private fun setItemValues() {
        storeName = intent.getStringExtra(Constants.STORE_NAME)!!
        var actionBar = supportActionBar
        actionBar?.title = storeName
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}