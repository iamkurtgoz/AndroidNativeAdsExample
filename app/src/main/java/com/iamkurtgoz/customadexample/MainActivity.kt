package com.iamkurtgoz.customadexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.formats.UnifiedNativeAd

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this)
        start()
    }

    private fun initAdBuilder(): AdRequest{
        val adRequest: AdRequest
        if (BuildConfig.DEBUG) {//Eğer proje debug modunda ise reklamları test olarak başlatacağız.
            adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
        } else {//Eğer release modunda ise normal başlatıyoruz.
            adRequest = AdRequest.Builder().build()
        }
        return adRequest
    }

    private fun start(){
        val builder: AdLoader.Builder = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")

        builder.forUnifiedNativeAd { unifiedNativeAd: UnifiedNativeAd? ->
            val smallTemplate: TemplateView = findViewById(R.id.smallTemplate)
            smallTemplate.setNativeAd(unifiedNativeAd) //Küçük reklamı ekledik

            val mediumTemplateView: TemplateView = findViewById(R.id.mediumTemplate)
            mediumTemplateView.setNativeAd(unifiedNativeAd) //Normal reklamı ekledik
        }

        val adLoader: AdLoader = builder.build()
        adLoader.loadAds(initAdBuilder(), 2) //loadAds kullandık çünkü 2 tane reklam ekledik.
    }
}
