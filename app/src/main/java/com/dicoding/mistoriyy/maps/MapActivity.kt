package com.dicoding.mistoriyy.maps

import MapsViewModelFactory
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mistoriyy.R
import com.dicoding.mistoriyy.data.di.StoriyResult
import com.dicoding.mistoriyy.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mapsViewModel: MapsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory: MapsViewModelFactory = MapsViewModelFactory.getInstance(this)
        mapsViewModel = ViewModelProvider(this, viewModelFactory)[MapsViewModel::class.java]

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupGetLocation()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val location = LatLng(-0.7893, 113.9213)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        setMapStyle()
    }


    private fun setMapStyle() {
        try {
            val success =
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (exception: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", exception)
        }
    }

    private fun setupGetLocation() {
        mapsViewModel.getStoriyLocation()
        mapsViewModel.location.observe(this) { result ->
            when (result) {
                is StoriyResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is StoriyResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    result.data.forEach { story ->
                        val latLng = LatLng(story?.lat!!, story.lon!!)
                        mMap.addMarker(
                            MarkerOptions()
                                .position(latLng)
                                .title(story.name)
                                .snippet(story.description)
                        )
                    }
                }

                is StoriyResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        binding.root,
                        "Gagal mendapatkan lokasi cerita",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MapsActivity"
    }
}