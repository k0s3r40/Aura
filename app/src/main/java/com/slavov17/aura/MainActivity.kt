package com.slavov17.aura

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: on login ok move to dashboard directly

        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (!bluetoothAdapter!!.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, 1)
        }
        val bondedDevices = bluetoothAdapter.bondedDevices

        if (bondedDevices.isEmpty()) {
            Toast.makeText(applicationContext, "Please Pair the Device first", Toast.LENGTH_SHORT).show()
        } else {
            for (iterator in bondedDevices) {
                Log.v("Main activity", iterator.address + " " +  iterator.name)

            }
        }

        if (true){
            val home = Intent(this, DashBoard::class.java)
            startActivity(home)
        }
        else{
            setContentView(R.layout.login)
        }

    }


}