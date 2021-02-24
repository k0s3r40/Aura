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

    private val bleScanner = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            Log.d("DeviceListActivity", "onScanResult: ${result?.device?.address} - ${result?.device?.name}")
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
            Log.d("DeviceListActivity", "onBatchScanResults:${results.toString()}")
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            Log.d("DeviceListActivity", "onScanFailed: $errorCode")
        }

    }
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    val bluetoothLeScanner: BluetoothLeScanner = bluetoothAdapter!!.bluetoothLeScanner

    fun startScan() {
        Log.d(TAG, "SCAN starting")
        bluetoothLeScanner.startScan(bleScanner)
        Log.d(TAG, "SCAN ongoing")
    }

    fun stopScan() {
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

