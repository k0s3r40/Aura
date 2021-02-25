package com.slavov17.aura

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.ArraySet
import android.util.Log
import java.util.*

class BleManager() {
    private val TAG = "BleManager"

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val bluetoothLeScanner: BluetoothLeScanner = bluetoothAdapter!!.bluetoothLeScanner

    //    private var scannedDevices:MutableSet<BluetoothDevice> =  MutableSet()
    var scannedDevices = mutableSetOf<BluetoothDevice>()

    val bleScanner = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            scannedDevices.add(result!!.device)
            Log.d(TAG, "onScanResult: ${result.device?.address} - ${result.device?.name}")

        }
    }

    private fun startScan() {
        scannedDevices.clear()
        Log.d(TAG, "SCAN starting")
        bluetoothLeScanner.startScan(bleScanner)
        Log.d(TAG, "SCAN ongoing")
    }

    private fun stopScan() {
        Log.d(TAG, "SCAN stopping")
        bluetoothLeScanner.stopScan(bleScanner)
        Log.d(TAG, "SCAN stopped")
        Log.i("DEVICES", scannedDevices.toString())
    }

    fun performScan(seconds: Long) {
        startScan()
        Thread.sleep(seconds)
        stopScan()
    }

    fun getDevices(): MutableSet<BluetoothDevice> {
        return scannedDevices
    }

    fun clearDevices() {
        scannedDevices.clear()
    }

    fun find_bluno(): BluetoothDevice? {
        for (device in scannedDevices) {
            if (device.name == "Bluno") {
                return device
            }
        }
        return null
    }


}

