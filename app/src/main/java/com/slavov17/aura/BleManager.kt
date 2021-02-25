package com.slavov17.aura

import android.bluetooth.*
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_bluetooth.*
import kotlin.collections.ArrayList

class BleManager(ble_list: ListView, context: Context) {

    private val TAG = "BleManager"
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val bluetoothLeScanner: BluetoothLeScanner = bluetoothAdapter!!.bluetoothLeScanner
    var adapter:BleAdapter?=null;
    var ble_list = ble_list
    var bleAdapters = ArrayList<BleObject>()
    var scannedDevices = mutableSetOf<BluetoothDevice>()

    val bleScanner = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            scannedDevices.add(result!!.device)
            adapter = BleAdapter(getAdapters(),  context)
            ble_list.adapter = adapter
            Log.d(TAG, "onScanResult: ${result.device?.address} - ${result.device?.name}")

        }
    }

    fun startScan() {
        ble_list.adapter = null
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

