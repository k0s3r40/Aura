package com.slavov17.aura.ui.bluetooth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slavov17.aura.R
import com.slavov17.aura.BleObject
import com.slavov17.aura.BleManager
import kotlinx.android.synthetic.main.ble_item.view.*
import kotlinx.android.synthetic.main.fragment_bluetooth.*


class BluetoothFragment : Fragment() {

    private lateinit var bluetoothViewModel: BluetoothViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bluetoothViewModel =
            ViewModelProvider(this).get(BluetoothViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_bluetooth, container, false)

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_loading.visibility = View.INVISIBLE
        var is_scanning: Boolean = false
        var bleObjects: ArrayList<BleObject>
        var adapter:BleAdapter?=null;

        val myBleManager = BleManager()
        scan_btn.setOnClickListener {
            if (!is_scanning) {
                bt_loading.visibility = View.VISIBLE
                myBleManager.startScan()
                is_scanning = true
                scan_btn.text = getString(R.string.stop);
            } else {
                bt_loading.visibility = View.INVISIBLE
                myBleManager.stopScan()
                is_scanning = false
                bleObjects = myBleManager.getAdapters()
                Log.i("FOUND AFTER SCAN", bleObjects.toString())
                adapter = BleAdapter(bleObjects,  requireContext())
                Log.i("FOUND AFTER SCAN", adapter!!.getCount().toString())
                ble_list.adapter = adapter
                scan_btn.text = getString(R.string.scan);
            }
        }

    }

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


}