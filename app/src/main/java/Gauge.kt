class Gauge constructor(var gauge_name: String,
                        var red_value: Float,
                        var orange_value: Float,
                        var yellow_value: Float,
                        var green_value: Float,
                        var current_value: Float,
                        val min_value: Float,
                        val max_value: Float) {
    val min_rotation: Int = 0;
    val max_rotation: Int = 300;
    var ratio  = max_value/max_rotation;

    fun calculate_ranges() {


    }


}