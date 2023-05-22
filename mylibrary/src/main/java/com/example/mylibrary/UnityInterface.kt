package com.example.mylibrary
import android.app.Application
import android.content.Intent
import com.unity3d.player.UnityPlayer
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

class UnityInterface {
    public fun presentMapView() {
        val activity = UnityPlayer.currentActivity;
        var intent = Intent(activity, MapsActivity::class.java)
        activity.startActivity(intent)
    }

    public fun deleteMapView() {

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