package com.itssvkv.loginsignup.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.itssvkv.loginsignup.R
import com.itssvkv.loginsignup.databinding.ActivityHomeScreenBinding
import com.itssvkv.loginsignup.ui.home.fragments.CategoryFragment
import com.itssvkv.loginsignup.ui.home.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var categoryFragment: CategoryFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }


    private fun init() {
        homeFragment = HomeFragment()
        categoryFragment = CategoryFragment()
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, homeFragment).commit()
                    true
                }

                R.id.category -> {
                    this.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, categoryFragment).commit()
                    true
                }


                else -> {
                    true
                }
            }
        }
    }

}