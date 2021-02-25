package com.slavov17.aura

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.slavov17.aura.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.ble_item.view.*
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.text.Charsets.UTF_8


class BleAdapter(bleObjects: ArrayList<BleObject>, context: Context) : BaseAdapter() {
    val BLUNO_SERIAL_UUID = UUID.fromString("0000dfb1-0000-1000-8000-00805f9b34fb")


    val TAG = "Bluetooth adapter"
    val ECO2 = "ECO2"
    val VOC = "VOC"
    val HUM = "HUM"
    val PSI = "PSI"
    val TMP = "TMP"


    var bleObjects = bleObjects
    var context = context


    val bleGattCallback: BluetoothGattCallback = object : BluetoothGattCallback() {
        val TAG = "GattCallBack"
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if (BluetoothProfile.STATE_CONNECTED == newState) {
                Log.i(TAG, "IS CONNECTED")
                gatt?.discoverServices()

            }else{
                Log.i(TAG, "IS DISCONNECTED")
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            Log.i("Service", status.toString())
            for (service in gatt?.services!!){
                 for (characteristic in service.characteristics){
                     if (characteristic.uuid ==BLUNO_SERIAL_UUID){

                         Log.i("Gatt", gatt.readDescriptor(characteristic.descriptors[0]).toString())

                         gatt.setCharacteristicNotification(characteristic, true)
                     }
                 }

            }

        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            Log.i("Characteristics", characteristic.toString())
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            if (characteristic != null) {
                val received_data = characteristic.getStringValue(0).split(":")
                val key = received_data[0]
                val value = received_data[1].split("_")[0].toFloat()
                if (key== ECO2){
                    Log.i("key", key)
                    Log.i("VALUE", value.toString())
                }
                val outputFile = File(context.cacheDir, "$key.txt")
                if (outputFile.exists()) {
                    outputFile.delete()
                }
                val inputStream = ByteArrayInputStream(value.toString().toByteArray(UTF_8))
                val outputStream = FileOutputStream(outputFile)
            }
        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val ble_object = bleObjects[position]
        var myView = LayoutInflater.from(context).inflate(R.layout.ble_item, null, true)
        myView.ble_mac.text = ble_object.ble_mac()
        myView.ble_name.text = ble_object.ble_name()
        myView.connect_btn.setOnClickListener {
            connectToDevice(ble_object)
        }
        return myView
    }

    override fun getItem(position: Int): Any {
        return bleObjects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return bleObjects.size
    }

    fun connectToDevice(ble_object: BleObject) {
        Log.i(TAG, ble_object.device.toString())
        ble_object.device.connectGatt(
            context,
            false,
            bleGattCallback
        )
    }


}