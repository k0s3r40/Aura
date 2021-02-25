package com.slavov17.aura

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.ble_item.view.*
import kotlinx.android.synthetic.main.fragment_bluetooth.*

class BleAdapter(bleObjects: ArrayList<BleObject>, context: Context) : BaseAdapter() {
    val TAG = "Bluetooth adapter"
    var bleObjects = bleObjects
    var context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val ble_object = bleObjects[position]
        var myView = LayoutInflater.from(context).inflate(R.layout.ble_item,null, true)
        myView.ble_mac.text = ble_object.ble_mac()
        myView.ble_name.text = ble_object.ble_name()
        myView.connect_btn.setOnClickListener {
            connectToDevice(ble_object.device)
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

    fun connectToDevice(device: BluetoothDevice){
        Log.i(TAG, device.toString())
    }
}