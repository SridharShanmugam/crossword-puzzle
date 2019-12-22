package com.sridhar.crosswordpuzzle.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sridhar.crosswordpuzzle.R
import com.sridhar.crosswordpuzzle.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        val splashViewModel = ViewModelProviders.of(this)[SplashViewModel::class.java]
        splashViewModel.canLaunchActivity.observe(this, Observer {
            if (it) {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
                finish()
            }
        })
    }
}
