package com.katja.storytour

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

object PositionManager : LocationListener {

    private var appContext: Context? = null
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    private val locationPermissionRequestCodes = mutableMapOf<Activity, Int>()

    fun init(context: Context) {
        appContext = context
        locationManager = appContext!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        requestLocationUpdates(context)
    }

    private fun requestLocationUpdates(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission not granted, request the permission
            val requestCode = generateUniqueRequestCode(context)
            locationPermissionRequestCodes[context as Activity] = requestCode
            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                requestCode
            )
        } else {
            // Permission already granted, request location updates
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                this
            )
        }
    }

    fun getCurrentLocation(context: Context): Location? {
        var currentLocation: Location? = null
        try {
            // Check if location permission is granted
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // Get the last known location from the GPS provider
                currentLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (currentLocation == null) {
                    // If GPS provider is not available, try with the network provider
                    currentLocation = locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                }
            }
        } catch (ex: SecurityException) {
            // Handle security exception
            ex.printStackTrace()
        }
        println(currentLocation)
        return currentLocation
    }

    fun calculateDistance(currentLocation: Location): Int {
        val destinationLocation = Location("Destination")
        destinationLocation.latitude = GeneralModel.location!!.waypoints[0].latitude
        destinationLocation.longitude = GeneralModel.location!!.waypoints[0].longitude
        println(currentLocation.distanceTo(destinationLocation).roundToInt())
        return currentLocation.distanceTo(destinationLocation).roundToInt()
    }


    private fun generateUniqueRequestCode(activity: Context): Int {
        // Generate a unique request code based on current time and activity hashcode
        return kotlin.math.abs(System.currentTimeMillis().toInt() + activity.hashCode())
    }

    override fun onLocationChanged(location: Location) {
        locationListener?.onLocationChanged(location)
    }

    override fun onProviderDisabled(provider: String) {
        // Handle provider disabled
    }

    override fun onProviderEnabled(provider: String) {
        // Handle provider enabled
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle?) {
        // Handle status changes if required
    }

    fun removeLocationUpdates() {
        locationManager?.removeUpdates(this)
    }
}