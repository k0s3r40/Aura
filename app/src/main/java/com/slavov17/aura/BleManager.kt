package com.slavov17.aura

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.util.Log
import android.view.View
import kotlin.collections.ArrayList

class BleManager() {
    private val TAG = "BleManager"

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val bluetoothLeScanner: BluetoothLeScanner = bluetoothAdapter!!.bluetoothLeScanner


    var bleAdapters = ArrayList<BleObject>()
    var scannedDevices = mutableSetOf<BluetoothDevice>()

    val bleScanner = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            scannedDevices.add(result!!.device)
            Log.d(TAG, "onScanResult: ${result.device?.address} - ${result.device?.name}")

        }
    }

    fun startScan() {
        scannedDevices.clear()
        Log.d(TAG, "SCAN starting")
        bluetoothLeScanner.startScan(bleScanner)
        Log.d(TAG, "SCAN ongoing")
    }

    fun stopScan() {
        Log.d(TAG, "SCAN stopping")
        bluetoothLeScanner.stopScan(bleScanner)
        Log.d(TAG, "SCAN stopped")
        Log.i("DEVICES", scannedDevices.toString())
    }

    fun performScan(seconds: Long, view: View) {
        view.visibility = View.VISIBLE
        startScan()
        Thread.sleep(seconds)
        stopScan()
        view.visibility = View.INVISIBLE
    }

    fun getDevices(): MutableSet<BluetoothDevice> {
        return scannedDevices
    }

    fun clearDevices() {
        scannedDevices.clear()
    }

    fun getAdapters(): ArrayList<BleObject> {
        bleAdapters.clear()
        for (device in scannedDevices) {
            bleAdapters.add(BleObject(device))
        }
        return bleAdapters
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

