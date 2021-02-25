package com.slavov17.aura.ui.bluetooth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slavov17.aura.R
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

        var isScanning: Boolean = false

        val myBleManager = BleManager(ble_list, requireContext())

        scan_btn.setOnClickListener {
            scanClickListener(myBleManager)
        }
        bt_scan_img.setOnClickListener {
            scanClickListener(myBleManager)
        }
        bt_loading.setOnClickListener {
            scanClickListener(myBleManager)
        }

    }

    fun scanClickListener(myBleManager: BleManager) {
        val isScanning: Boolean = bt_loading.visibility == View.VISIBLE
        if (!isScanning) {
            bt_loading.visibility = View.VISIBLE
            myBleManager.startScan()
            scan_btn.text = getString(R.string.stop);
        } else {
            bt_loading.visibility = View.INVISIBLE
            myBleManager.stopScan()
            scan_btn.text = getString(R.string.scan);
        }
    }


}