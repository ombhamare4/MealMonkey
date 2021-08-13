package com.rubiks.mealmonkey.activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rubiks.mealmonkey.R
import com.rubiks.mealmonkey.fragment.*

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var appBarLayout: AppBarLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frame: FrameLayout
    lateinit var floatingActionButton: FloatingActionButton
    var previousMenuItem: MenuItem? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
//        appBarLayout = findViewById(R.id.appBarLayout)
//        toolbar = findViewById(R.id.toolbar)
        frame = findViewById(R.id.frame)
        floatingActionButton = findViewById(R.id.floatingHomeButton)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        openHomeView()
        floatingActionButton.setOnClickListener {
            openHomeView()
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {
            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when (it.itemId) {
                R.id.home -> openHomeView()
                R.id.menu -> openMenuView()
                R.id.offer -> openOfferMenu()
                R.id.profile -> openProfileMenu()
                R.id.more -> openMoreMenu()
                else -> throw AssertionError()
            }
        }

    }

    fun openHomeView(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment())
            .commit()
        supportActionBar?.title = "Home"
        return true
    }

    fun openMenuView(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frame, MenuFragment())
            .commit()
        supportActionBar?.title = "Menu"
        return true
    }

    fun openOfferMenu(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frame, OfferFragment())
            .commit()
        supportActionBar?.title = "Offer"
        return true
    }

    fun openProfileMenu(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frame, ProfileFragment())
            .commit()
        supportActionBar?.title = "Profile"
        return true
    }

    fun openMoreMenu(): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frame, MoreFragment())
            .commit()
        supportActionBar?.title = "More"
        return true
    }
//    fun setToolBar() {
//        setSupportActionBar(toolbar)
//        supportActionBar?.setHomeButtonEnabled(true)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }
}