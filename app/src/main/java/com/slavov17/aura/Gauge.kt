import android.widget.ImageView

class Gauge(val arrow: ImageView,
            val background: ImageView,
            val gauge_name: String,
            val red_value: ClosedRange<Float>,
            val orange_value: ClosedRange<Float>,
            val yellow_value: ClosedRange<Float>,
            val green_value: ClosedRange<Float>,
            var current_value: Float,
            val min_value: Float,
            val max_value: Float) {

    val gauge_green = 0xFF66C2A5.toInt()
    val gauge_yellow = 0xFFFDD448.toInt()
    val gauge_orange = 0xFFF5A947.toInt()
    val gauge_red = 0xFFD53E4F.toInt()

    val base_rotation = -150;

    val min_rotation: Int = 0;
    val max_rotation: Int = 300;
    var ratio = max_rotation / max_value;

    fun calculate_rotation(): Float{
        return base_rotation+(current_value * ratio)
    }

    fun rotate_gauge(){
        arrow.rotation = calculate_rotation()
        arrow.setColorFilter(get_color());
        background.setColorFilter(get_color());
    }


    fun get_color(): Int {
        when (current_value) {
            in red_value -> return gauge_red
            in orange_value -> return gauge_orange
            in yellow_value -> return gauge_yellow
            in green_value -> return gauge_green
            else -> return gauge_green

        }
    }


}