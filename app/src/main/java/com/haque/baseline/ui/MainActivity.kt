package com.haque.baseline.ui

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.haque.baseline.R
import com.haque.baseline.utils.GeocoderWrapper
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.migration.CustomInjection.inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var geocoderWrapper: GeocoderWrapper
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val navController = navHostFragment.navController

        val navView: BottomNavigationView = findViewById(R.id.bottomNav)
        navView.setupWithNavController(navController)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkIfLocationPermissionGranted()
    }

    // Handles User response
    private val locationRequestPermissionLauncher = this.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // User denied previously and has checked "Never ask again"
                // show a toast with steps to manually enable it via settings
                Toast.makeText(
                    applicationContext,
                    "Permission Denied. Use search bar instead, or go to your settings to allow location use.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // And this code snippet demonstrates the recommended process to check for a permission and to
    // request a permission from the user when necessary:
    // Checks and Requests User for Permission
    private fun checkIfLocationPermissionGranted() {
        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        locationRequestPermissionLauncher.launch(arrayOf())
//                        val name = geocoderWrapper.getCurrentLocation(location!!.latitude, location.longitude)
//                        Timber.d("The name of the current location is: $name")
                        Timber.d(location.toString())
                    }
            }
            // this is the recommended flow from Google - you're informing the user what the app is for
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                PermissionRationaleDialogFragment().show(
                    this.supportFragmentManager, PermissionRationaleDialogFragment.TAG
                )

                Toast.makeText(
                    applicationContext,
                    "Location Permission not granted.  That's alright,  use search bar instead, or switch permissions in settings!",
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                locationRequestPermissionLauncher.launch(
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
                )
            }
        }
    }

}

class PermissionRationaleDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "PermissionRationaleDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(
                "With Location Permissions on, your weather will be updated to your locale upon opening." +
                        "Otherwise, you can manually search."
            )
                .setPositiveButton("Okay",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}