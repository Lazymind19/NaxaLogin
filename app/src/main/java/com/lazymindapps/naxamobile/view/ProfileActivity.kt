package com.lazymindapps.naxamobile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.lazymindapps.naxamobile.NaxaApplication
import com.lazymindapps.naxamobile.R
import com.lazymindapps.naxamobile.databinding.ActivityProfileBinding
import com.lazymindapps.naxamobile.viewmodel.NaxaViewModel
import com.lazymindapps.naxamobile.viewmodel.NaxaViewModelFactory

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var appBarConfiguration: AppBarConfiguration

    val viewModel: NaxaViewModel by viewModels{
        NaxaViewModelFactory((application as NaxaApplication).repo)
    }

    val navController by lazy {
        Navigation.findNavController(this,R.id.navHostFragment)

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)




    }



    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }

    }

}