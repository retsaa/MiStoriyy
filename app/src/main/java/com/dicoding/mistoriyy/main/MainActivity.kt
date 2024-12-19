package com.dicoding.mistoriyy.main




import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.dicoding.mistoriyy.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)

        // Hubungkan BottomNavigationView dengan NavController
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }
}
