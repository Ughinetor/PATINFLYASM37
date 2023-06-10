package com.example.patinflyasm37.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.patinflyasm37.R
import com.example.patinflyasm37.logpage.SignLogActivity
import com.google.android.material.navigation.NavigationView


class MenuActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navview)

        setSupportActionBar(findViewById(R.id.toolbar3))

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        findViewById<NavigationView>(R.id.navview)
            .setupWithNavController(navController)


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_home -> {
                    val currentFragment =
                        navHostFragment.childFragmentManager.fragments.firstOrNull()
                    when (currentFragment) {
                        is HomeFragment -> {
                            Toast.makeText(this, "Already in the home", Toast.LENGTH_SHORT).show()
                        }
                        is RentFragment -> {
                            navHostFragment.findNavController().navigate(R.id.action_rentFragment_to_homeFragment)
                        }
                        else -> {
                            navHostFragment.findNavController().navigate(R.id.action_changeFragment_to_homeFragment)
                        }

                    }

                }

                R.id.nav_rent -> {
                    val currentFragment =
                        navHostFragment.childFragmentManager.fragments.firstOrNull()
                    when (currentFragment) {
                        is RentFragment -> {
                            Toast.makeText(this, "Already in the rent section", Toast.LENGTH_SHORT).show()
                        }
                        is HomeFragment -> {
                            navHostFragment.findNavController().navigate(R.id.action_homeFragment_to_rentFragment)
                        }
                        else -> {
                            navHostFragment.findNavController().navigate(R.id.action_changeFragment_to_rentFragment)
                        }

                    }

                }

                R.id.nav_change -> {
                    val currentFragment =
                        navHostFragment.childFragmentManager.fragments.firstOrNull()
                    when (currentFragment) {
                        is ChangeFragment -> {
                            Toast.makeText(this, "Already in the settings section", Toast.LENGTH_SHORT).show()
                        }
                        is HomeFragment -> {
                            navHostFragment.findNavController().navigate(R.id.action_homeFragment_to_changeFragment)
                        }
                        else -> {
                            navHostFragment.findNavController().navigate(R.id.action_rentFragment_to_changeFragment)
                        }

                    }

                }

                R.id.logout -> {
                    startActivity(Intent(this, SignLogActivity::class.java))
                    finish()
                }

            }
            true


        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,findViewById<DrawerLayout>(R.id.drawerLayout))
    }

}




