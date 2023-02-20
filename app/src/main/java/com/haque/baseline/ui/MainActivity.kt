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

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Location Denied. Use search bar, or go to your settings to allow location use.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    private fun checkIfLocationPermissionGranted() {
        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        val bundle = Bundle()
                        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                        val currentPlace = geocoderWrapper.getCurrentLocation(location!!.latitude, location.longitude)

//                        bundle.putSerializable("place", )
                        Timber.d("SCREAMING - $location.toString()")
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
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                Timber.d("SCREAMING else block hit inside checkIfLocationPermissionGranted")
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