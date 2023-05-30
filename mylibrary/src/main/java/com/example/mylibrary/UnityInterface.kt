package com.example.mylibrary
import android.content.Intent
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import com.unity3d.player.UnityPlayer



public fun presentMapView() {
    val activity = UnityPlayer.currentActivity;
    var intent = Intent(activity, MapsActivity::class.java)
    UnityPlayer.currentActivity.applicationContext.startActivity(intent)
}


class UnityInterface {


    public fun presentMapView() {
        val activity = UnityPlayer.currentActivity;
       var intent = Intent(activity, MapsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
       UnityPlayer.currentActivity.applicationContext.startActivity(intent)
    }


    public fun deleteMapView() {

    }

    public fun testMethod() : Int {
        val util = Utility();
        var total = util.Add(2,3)
        return total
    }

    public fun testString() : String {
        return "mizuki"
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
