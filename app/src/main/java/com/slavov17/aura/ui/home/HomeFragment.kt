package com.slavov17.aura.ui.home

import Gauge
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.slavov17.aura.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val eco_2_gauge = Gauge(
                gauge_name = "eCO2",
                red_value = 2000F..5000F,
                orange_value = 1000F..1999F,
                yellow_value = 400F..999F,
                green_value = 0F..399F,
                current_value = 0F,
                min_value = 0F,
                max_value = 5000F)

        val voc_gauge = Gauge(
                gauge_name = "VOC",
                red_value = 1F..3F,
                orange_value = 0.5F..0.99F,
                yellow_value = 0.3F..0.49F,
                green_value = 0F..2.99F,
                current_value = 0F,
                min_value = 0F,
                max_value = 3F)

        val humidity_gauge = Gauge(
                gauge_name = "Humidity",
                red_value =60F..100F,
                orange_value = 51F..59F,
                yellow_value = 0F..29F,
                green_value = 30F..50F,
                current_value = 0F,
                min_value = 0F,
                max_value = 100F)

        val pressure_gauge = Gauge(
                gauge_name = "BP",
                red_value =2001F..3000F,
                orange_value = 1501F..2000F,
                yellow_value = 1014F..1500F,
                green_value = 0F..1014F,
                current_value = 0F,
                min_value = 0F,
                max_value = 3000F)

        Thread {
            val eco_2_arrow_canvas: ImageView = root.findViewById(R.id.eco2_arrow_canvas)
            val eco_2_gauge_back: ImageView = root.findViewById(R.id.eco2_gauge_back)

            val voc_arrow_canvas: ImageView = root.findViewById(R.id.voc_arrow_canvas)
            val voc_gauge_back: ImageView = root.findViewById(R.id.voc_gauge_back)

            val hum_arrow_canvas: ImageView = root.findViewById(R.id.hum_arrow_canvas)
            val hum_gauge_back: ImageView = root.findViewById(R.id.hum_gauge_back)

            val pressure_arrow_canvas: ImageView = root.findViewById(R.id.pressure_arrow_canvas)
            val pressure_gauge_back: ImageView = root.findViewById(R.id.pressure_gauge_back)



            for (i in 1..300) {
                eco_2_arrow_canvas.setColorFilter(get_colors(i));
                eco_2_gauge_back.setColorFilter(get_colors(i));
                eco_2_arrow_canvas.rotation = -150 + i.toFloat()

                voc_arrow_canvas.setColorFilter(get_colors(i));
                voc_gauge_back.setColorFilter(get_colors(i));
                voc_arrow_canvas.rotation = -150 + i.toFloat()

                hum_arrow_canvas.setColorFilter(get_colors(i));
                hum_gauge_back.setColorFilter(get_colors(i));
                hum_arrow_canvas.rotation = -150 + i.toFloat()

                pressure_arrow_canvas.setColorFilter(get_colors(i));
                pressure_gauge_back.setColorFilter(get_colors(i));
                pressure_arrow_canvas.rotation = -150 + i.toFloat()

                Thread.sleep(3)
            }

            for (i in 300 downTo 1) {
                eco_2_arrow_canvas.rotation = -150 + i.toFloat()
                eco_2_arrow_canvas.setColorFilter(get_colors(i));
                eco_2_gauge_back.setColorFilter(get_colors(i));

                voc_arrow_canvas.setColorFilter(get_colors(i));
                voc_gauge_back.setColorFilter(get_colors(i));
                voc_arrow_canvas.rotation = -150 + i.toFloat()

                hum_arrow_canvas.setColorFilter(get_colors(i));
                hum_gauge_back.setColorFilter(get_colors(i));
                hum_arrow_canvas.rotation = -150 + i.toFloat()

                pressure_arrow_canvas.setColorFilter(get_colors(i));
                pressure_gauge_back.setColorFilter(get_colors(i));
                pressure_arrow_canvas.rotation = -150 + i.toFloat()

                Thread.sleep(1)

            }

        }.start()

        return root
    }

    fun get_colors(value: Int): Int {

        var gauge_green = 0xFF66C2A5.toInt()
        var gauge_yellow = 0xFFFDD448.toInt()
        var gauge_orange = 0xFFF5A947.toInt()
        var gauge_red = 0xFFD53E4F.toInt()
        var transparent = 0xFFFDD448.toInt()
        if (value >= 120) {
            return gauge_red
        }
        if (value > 60) {
            return gauge_orange
        }

        if (value > 16) {
            return gauge_yellow
        }

        if (value > 0) {
            return gauge_green
        }

        return R.color.gauge_red
    }
}