package com.example.mylibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mylibrary.databinding.ActivityMapsBinding
import com.unity3d.player.UnityPlayer

public interface AsynkTaskInterface {
    public fun setMarker(lat: Double, lon: Double, title: String);
}

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, AsynkTaskInterface {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()

        Handler(Looper.getMainLooper()).postDelayed({
            UnityPlayer.UnitySendMessage("GetWayspots", "RequestAreas", "from ios")
        }, 1500)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    public override fun setMarker(lat: Double, lon: Double, title: String) {
        val markerOptions = MarkerOptions()
        val latlng = LatLng(lat, lon)
        markerOptions.position(latlng)
        markerOptions.title(title)
        mMap.addMarker(markerOptions)
    }
}