package com.slavov17.aura.ui.home

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
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
        Thread {
            val eco_2_arrow_canvas: ImageView = root.findViewById(R.id.eco2_arrow_canvas)
            val eco_2_gauge_back: ImageView = root.findViewById(R.id.eco2_gauge_back)


            for (i in 1..300) {
                eco_2_arrow_canvas.setColorFilter(get_colors(i));
                eco_2_gauge_back.setColorFilter(get_colors(i));
                eco_2_arrow_canvas.rotation = -150 + i.toFloat()
                Thread.sleep(3)
            }

            for (i in 300 downTo 1) {
                eco_2_arrow_canvas.rotation = -150 + i.toFloat()
                eco_2_arrow_canvas.setColorFilter(get_colors(i));
                eco_2_gauge_back.setColorFilter(get_colors(i));

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