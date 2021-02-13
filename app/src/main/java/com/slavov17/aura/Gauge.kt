class Gauge(var gauge_name: String,
            var red_value: ClosedRange<Float>,
            var orange_value: ClosedRange<Float>,
            var yellow_value: ClosedRange<Float>,
            var green_value: ClosedRange<Float>,
            var current_value: Float,
            val min_value: Float,
            val max_value: Float) {

    val gauge_green = 0xFF66C2A5.toInt()
    val gauge_yellow = 0xFFFDD448.toInt()
    val gauge_orange = 0xFFF5A947.toInt()
    val gauge_red = 0xFFD53E4F.toInt()

    val min_rotation: Int = 0;
    val max_rotation: Int = 300;
    var ratio = max_rotation / max_value;

    fun calculate_rotation(): Int {
        return (current_value * ratio).toInt()
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