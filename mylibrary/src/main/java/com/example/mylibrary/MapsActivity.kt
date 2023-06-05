package com.example.mylibrary

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.unity3d.player.UnityPlayer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json



public class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)

        supportActionBar?.hide()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val button: Button = findViewById<Button>(R.id.my_button)
        button.setOnClickListener { finish() }


        val mapFragment = getSupportFragmentManager()
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission(),
            ActivityResultCallback<Boolean> { isGranted: Boolean ->
                if (isGranted) {
                    fusedLocation()
                }
            })

    private fun fusedLocation() {
        UnityPlayer.UnitySendMessage("GetWaysports","RequestAreas","")
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        mMap.isMyLocationEnabled = true

        val locationResult: Task<Location> = fusedLocationClient.getLastLocation()
        locationResult.addOnSuccessListener(this,
            OnSuccessListener<Location?> { location -> // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    val currentLocation = LatLng(location.latitude, location.longitude)
                    mMap.addMarker(
                        MarkerOptions().position(currentLocation)
                            .title("Marker in current location")
                    )
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f))
                }
            })
    }

        override fun onMapReady(p0: GoogleMap): Unit {
            mMap = p0

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            } else {
                fusedLocation()


                val intent = getIntent()
                val geoJson = intent.getStringExtra("geo")

                geoJson?.let {
                    val mapInfo  = Json.decodeFromString<AreaList>(geoJson)
                    mapInfo.features.forEach {
                                val position = LatLng(it.lat, it.lon)

                                mMap.addMarker(MarkerOptions().position(position).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title(it.title))
                            }
                        }
            }
        }
}