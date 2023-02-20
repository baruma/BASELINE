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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Handles User response
    private val locationRequestPermissionLauncher = this.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                // Get current location and convert it to place data
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // User denied previously and has checked "Never ask again"
                // show a toast with steps to manually enable it via settings
                Toast.makeText(
                    applicationContext,
                    "Location Permission Denied.  Use search bar instead, or go to your settings to allow location use.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

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
                        // Got last known location. In some rare situations this can be null.

                        Toast.makeText(applicationContext, "location block hit", Toast.LENGTH_SHORT)
                            .show()
                        Timber.d(location.toString())
                    }
            }
            // this is the recommended flow - you're informing the user what the app is for
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
                Timber.d("Else block hit")
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
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("The app experience will be enhanced if you allow location " +
                    "permissions.  This way, your weather will be updated once you enter the " +
                    "app.  Otherwise, you can manually search for places if you prefer.")
                .setPositiveButton("Okay",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}