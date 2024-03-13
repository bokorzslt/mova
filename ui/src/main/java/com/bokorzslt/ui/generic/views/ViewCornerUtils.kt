package com.bokorzslt.ui.generic.views

/**
 * Util methods for creating rounded view corners.
 */
object ViewCornerUtils {
    /**
     * Builds an array with radius values based on the given [ViewCorner].
     * E.g.: TOP_LEFT -> new float[] {4.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
     * TOP_RIGHT -> new float[] {0.0, 0.0, 4.0, 4.0, 0.0, 0.0, 0.0, 0.0}
     */
    fun getRadiusArrayByCorner(corner: ViewCorner, roundRadius: Float): FloatArray {
        val radiusArr = FloatArray(8)
        radiusArr[2 * corner.ordinal] = roundRadius
        radiusArr[2 * corner.ordinal + 1] = roundRadius
        return radiusArr
    }

    fun getRadiusArrayByCorners(corners: List<ViewCorner>, roundRadius: Float): FloatArray {
        val radiusArr = FloatArray(8)
        for (viewCorner in corners) {
            radiusArr[2 * viewCorner.ordinal] = roundRadius
            radiusArr[2 * viewCorner.ordinal + 1] = roundRadius
        }
        return radiusArr
    }

    enum class ViewCorner {
        TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT
    }
}
