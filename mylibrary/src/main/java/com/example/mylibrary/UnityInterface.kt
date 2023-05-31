package com.example.mylibrary
import android.content.Intent
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import com.unity3d.player.UnityPlayer



class UnityInterface {


    public fun presentMapView(geoJson: String) {
        val activity = UnityPlayer.currentActivity;
       var intent = Intent(activity, MapsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra("geo", geoJson)
       UnityPlayer.currentActivity.applicationContext.startActivity(intent)
    }

    public fun receiveMapInfo(geoJson: String, title: String) {

        geoJson.let {
            val mapInfo  = Json.decodeFromString<Root>(geoJson)
            mapInfo.features.forEach {
                val geometry = it.geometry.coordinates.forEach {
                    it.forEach {

                        return
                    }
                }
            }
        }
    }
}
