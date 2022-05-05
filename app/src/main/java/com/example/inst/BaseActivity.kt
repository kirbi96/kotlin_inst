package com.example.inst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseActivity(val navNumber: Int): AppCompatActivity() {
    fun setupBottomNavigation () {
        val bnv = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bnv.setOnItemSelectedListener { item ->
            val nextActivity =
                when(item.itemId) {
                    R.id.nav_item_home -> MainActivity::class.java
                    R.id.nav_item_search-> SearchActivity::class.java
                    R.id.nav_item_share-> ShareActivity::class.java
                    R.id.nav_item_likes-> LikesActivity::class.java
                    R.id.nav_item_profile-> ProfileActivity::class.java
                    else -> null
                }

            if (nextActivity != null){
                val intent = Intent(this, nextActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0, 0)
                true
            } else false
        }

        bnv.menu.getItem(navNumber).isChecked = true
    }
}