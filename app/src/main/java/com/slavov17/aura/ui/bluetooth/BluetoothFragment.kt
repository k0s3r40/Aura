package com.slavov17.aura.ui.bluetooth

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
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
        bt_loading.visibility = View.INVISIBLE
        var is_scanning: Boolean = false
        var bleAdapters: ArrayList<BLEAdapter>

        val myBleManager = BleManager()
        scan_btn.setOnClickListener {
            if (!is_scanning) {
                bt_loading.visibility = View.VISIBLE
                myBleManager.startScan()
                is_scanning = true
                scan_btn.text = getString(R.string.stop);
            }else{
                bt_loading.visibility = View.INVISIBLE
                myBleManager.stopScan()
                is_scanning = false
                bleAdapters = myBleManager.getAdapters()
                Log.i("FOUND AFTER SCAN", myBleManager.scannedDevices.toString())
                scan_btn.text = getString(R.string.scan);
            }
        }
    }


}