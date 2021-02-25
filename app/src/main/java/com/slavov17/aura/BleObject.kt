package com.slavov17.aura

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt

class BleObject(val device: BluetoothDevice) {
    var gatt: BluetoothGatt? = null
    val name_placeholder = "Device"



    fun ble_name(): String? {
        if (device.name != null) {
            return device.name
        } else {
            return name_placeholder
        }

    }

    fun ble_mac(): String? {
        return device.address
    }

}