package com.slavov17.aura.ui.dashboard

import Gauge
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.slavov17.aura.R
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    lateinit var eco_2_gauge: Gauge;
    lateinit var voc_gauge: Gauge;
    lateinit var humidity_gauge: Gauge;
    lateinit var pressure_gauge: Gauge;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eco_2_gauge = Gauge(
            arrow = eco2_arrow_canvas,
            background = eco2_gauge_back,
            gauge_name = "eCO2",
            red_value = 2000F..5000F,
            orange_value = 1000F..1999F,
            yellow_value = 400F..999F,
            green_value = 0F..399F,
            current_value = 0F,
            min_value = 0F,
            max_value = 5000F
        )

        val voc_gauge = Gauge(
            arrow = voc_arrow_canvas,
            background = voc_gauge_back,
            gauge_name = "VOC",
            red_value = 1F..3F,
            orange_value = 0.5F..0.99F,
            yellow_value = 0.3F..0.49F,
            green_value = 0F..0.29F,
            current_value = 0F,
            min_value = 0F,
            max_value = 3F
        )

        val humidity_gauge = Gauge(
            arrow = hum_arrow_canvas,
            background = hum_gauge_back,
            gauge_name = "Humidity",
            red_value = 60F..100F,
            orange_value = 51F..59F,
            yellow_value = 0F..29F,
            green_value = 30F..50F,
            current_value = 0F,
            min_value = 0F,
            max_value = 100F
        )

        val pressure_gauge = Gauge(
            arrow = pressure_arrow_canvas,
            background = pressure_gauge_back,
            gauge_name = "BP",
            red_value = 2001F..3000F,
            orange_value = 1501F..2000F,
            yellow_value = 1014F..1500F,
            green_value = 0F..1014F,
            current_value = 0F,
            min_value = 0F,
            max_value = 3000F
        )



        eco_2_gauge.current_value = 3000F
        eco_2_gauge.rotate_gauge()
        voc_gauge.current_value = 0.29F
        voc_gauge.rotate_gauge()
        humidity_gauge.current_value = 30F
        humidity_gauge.rotate_gauge()
        pressure_gauge.current_value = 1000F
        pressure_gauge.rotate_gauge()
    }


}