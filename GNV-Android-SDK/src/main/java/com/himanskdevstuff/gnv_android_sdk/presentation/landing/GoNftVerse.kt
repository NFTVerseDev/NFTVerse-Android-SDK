package com.himanskdevstuff.gnv_android_sdk.presentation.landing

import android.content.Context
import android.content.Intent
import com.himanskdevstuff.gnv_android_sdk.R
import com.himanskdevstuff.gnv_android_sdk.constants.Constants
import com.himanskdevstuff.gnv_android_sdk.presentation.LauncherActivity
import com.himanskdevstuff.gnv_android_sdk.presentation.util.StoreDetails

class GoNftVerse(val context: Context) {
    private var customTheme = R.style.Theme_GoNftVerse
    private var storeDetails = StoreDetails()

    fun setStoreDetails(yourStoreDetails: StoreDetails){
        storeDetails = yourStoreDetails
    }
    fun setTheme(yourCustomTheme: Int){
        customTheme = yourCustomTheme
    }
    fun startSDK(){
        val intent = Intent(context, LauncherActivity::class.java)
        intent.putExtra(Constants.CUSTOM_THEME,customTheme)
        intent.putExtra(Constants.STORE_NAME,storeDetails.storeName)
        intent.putExtra(Constants.STORE_ID, storeDetails.storeId)
        context.startActivity(intent)
    }
}