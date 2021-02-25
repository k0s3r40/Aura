package com.slavov17.aura

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.ble_item.view.*

class BleAdapter(bleObjects: ArrayList<BleObject>, context: Context) : BaseAdapter() {
    var bleObjects = bleObjects
    var context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.i("BLE ADATPTER","CREATING STUFF")
        val ble_object = bleObjects[position]
        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var myView = LayoutInflater.from(context).inflate(R.layout.ble_item,null, true)
//            var myView = LayoutInflater.inflate(R.layout.ble_item, null)
        myView.ble_mac.text = ble_object.ble_mac()
        myView.ble_name.text = ble_object.ble_name()

        return myView
    }

    override fun getItem(position: Int): Any {
        return bleObjects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        Log.i("BLE ADATPTER",bleObjects.toString())
        Log.i("BLE ADATPTER",context.toString())
        return bleObjects.size
    }

}