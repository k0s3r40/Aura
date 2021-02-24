package com.slavov17.aura

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: on login ok move to dashboard directly

        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (!bluetoothAdapter!!.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, 1)
        }

        if (true) {
            val home = Intent(this, DashBoard::class.java)
            startActivity(home)
        } else {
            setContentView(R.layout.login)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        Log.d("Main activity", "onStart()")
        super.onStart()
        when (PermissionChecker.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)) {
            PackageManager.PERMISSION_GRANTED -> Log.i("ACCESS_FINE_LOCATION", "OK")
            else -> requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        when (PermissionChecker.checkSelfPermission(this, Manifest.permission.BLUETOOTH)) {
            PackageManager.PERMISSION_GRANTED -> Log.i("BLUETOOTH", "OK")
            else -> requestPermissions(arrayOf(Manifest.permission.BLUETOOTH), 1)
        }
        when (PermissionChecker.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)) {
            PackageManager.PERMISSION_GRANTED -> Log.i("BLUETOOTH_ADMIN", "OK")
            else -> requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_ADMIN), 1)
        }
        init_ble_and_scan()
    }

    fun init_ble_and_scan() {
        val BleManager = BleManager()
        BleManager.performScan(10000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> when (grantResults) {
                intArrayOf(PackageManager.PERMISSION_GRANTED) -> {
                    Log.d("ScanDevices", "onRequestPermissionsResult(PERMISSION_GRANTED)")
                }
                else -> {
                    Log.d("ScanDevices", "onRequestPermissionsResult(not PERMISSION_GRANTED)")
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}