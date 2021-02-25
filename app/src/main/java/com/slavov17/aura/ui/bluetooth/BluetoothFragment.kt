package com.slavov17.aura.ui.bluetooth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.slavov17.aura.R
import com.slavov17.aura.BLEAdapter
import com.slavov17.aura.BleManager
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

        var ble_devices: ArrayList<BLEAdapter>

        val myBleManager = BleManager()
        scan_btn.setOnClickListener {
            Thread {
                myBleManager.performScan(5000)
                Log.i("DANG", myBleManager.find_bluno().toString())
            }.start()
        }
    }


}