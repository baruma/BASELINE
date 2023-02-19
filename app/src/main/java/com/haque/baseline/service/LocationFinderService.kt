//package com.haque.baseline.service
//
//import android.Manifest
//import android.app.Application
//import android.content.pm.PackageManager
//import android.location.Location
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.haque.baseline.domain.PlaceFinder
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import javax.inject.Inject
//
//@ExperimentalCoroutinesApi
//class LocationFinderService @Inject constructor(
//    private val fusedLocationClient: FusedLocationProviderClient,
//    private val application: Application
//) : PlaceFinder {
//
//    private val userLocation: Location? = null
//
//
//    /* TODO: This goes will the runtime permissions docs: https://developer.android.com/training/permissions/requesting#manage-request-code-yourself
//         // Before you perform the actual permission request, check whether your app
//        // already has the permissions, and whether your app needs to show a permission
//        // rationale dialog.
//     */
//
////   TODO:  registerForActivityResult - need to get this from activity.  I've been trying to get it from application.
//    val locationPermissionRequest = registerForActivityResult(
//        ActivityResultContracts.RequestMultiplePermissions()
//    ) { permissions ->
//        when {
//            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
//                // Only approximate location access granted.
//            }
//            else -> {
//                // No location access granted.
//            }
//        }
//    }
//
//        // gets last known location> use getCurrentLocation
//    override suspend fun getLastKnownLocation(): Location? {
//        if (ActivityCompat.checkSelfPermission(
//                application,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                application,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//
//
//    }
//
//
//}
//
