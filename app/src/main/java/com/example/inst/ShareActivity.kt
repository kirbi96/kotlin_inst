package com.example.inst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ShareActivity : BaseActivity(2) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupBottomNavigation()
    }
}