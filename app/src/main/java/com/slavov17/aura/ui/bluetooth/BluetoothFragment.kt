package com.slavov17.aura.ui.bluetooth

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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

        var bleAdapters: ArrayList<BLEAdapter>

        val myBleManager = BleManager()
        scan_btn.setOnClickListener {

            myBleManager.performScan(5000)
            bleAdapters = myBleManager.getAdapters()
        }
    }


}