package com.example.ujiansk50

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ujiansk50.databinding.ActivityMainBinding
import com.example.ujiansk50.ui.user.ProfileActivity

class MainActivity : AppCompatActivity() {
    private lateinit var activityHomeBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        activityHomeBinding.imgProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // call and set sharedpreferences
        val preferences: SharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE)
        val display: String? = preferences.getString("display", "")
        activityHomeBinding.txtWelcome.text = getString(R.string.home_username, display)

        // set view pager in main activity
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tab.setupWithViewPager(activityHomeBinding.viewPager)
    }

}