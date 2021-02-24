package com.slavov17.aura

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.Log

class BleManager() {
    private val TAG = "BleManager"

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val bluetoothLeScanner: BluetoothLeScanner = bluetoothAdapter!!.bluetoothLeScanner

    val bleScanner = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            Log.d(TAG, "TEEEEEST")
//            super.onScanResult(callbackType, result)
            Log.d(TAG, "onScanResult: ${result?.device?.address} - ${result?.device?.name}")
        }
    }

    private fun startScan() {
        Log.d(TAG, "SCAN starting")
        bluetoothLeScanner.startScan(bleScanner)
        Log.d(TAG, "SCAN ongoing")
    }

    private fun stopScan() {
        Log.d(TAG, "SCAN stopping")
        bluetoothLeScanner.stopScan(bleScanner)
        Log.d(TAG, "SCAN stopped")
    }

    fun performScan(seconds:Long){
        startScan()
        Thread.sleep(seconds)
        stopScan()
    }
}

