package com.slavov17.aura

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: on login ok move to dashboard directly
        if (true){
            val home = Intent(this, DashBoard::class.java)
            startActivity(home)
        }
        else{
            setContentView(R.layout.login)
        }

    }


}