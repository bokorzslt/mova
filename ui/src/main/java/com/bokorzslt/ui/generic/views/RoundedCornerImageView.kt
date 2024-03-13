package com.bokorzslt.ui.generic.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bokorzslt.ui.R

/**
 * Custom ImageView with rounded corners.
 */
class RoundedCornerImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    private val path = Path()
    private val rect = RectF(0f, 0f, 0f, 0f)
    private val backgroundPaint = Paint()
    private var roundRadius = 0f

    /**
     * By default all corners are normal, in any other cases just the marked corners.
     */
    private var roundedTopLeft = false
    private var roundedTopRight = false
    private var roundedBottomLeft = false
    private var roundedBottomRight = false
    private lateinit var roundedCorners: MutableList<ViewCornerUtils.ViewCorner>

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        readAttributes(context, attrs)
        buildRoundedCornersArray()
        backgroundPaint.color = Color.TRANSPARENT
    }

    override fun setBackgroundColor(color: Int) {
        setBackgroundLookAndFeel(color, roundRadius)
    }

    private fun setBackgroundLookAndFeel(color: Int, radius: Float) {
        backgroundPaint.color = color
        roundRadius = radius
        forceLayout()
    }

    override fun onDraw(canvas: Canvas) {
        if (roundRadius > 0) {
            rect.right = this.width.toFloat()
            rect.bottom = this.height.toFloat()
            path.reset()
            path.addRoundRect(rect, roundRadius, roundRadius, Path.Direction.CW)
            path.addRoundRect(
                rect,
                ViewCornerUtils.getRadiusArrayByCorners(roundedCorners, roundRadius),
                Path.Direction.CW
            )
            canvas.drawPath(path, backgroundPaint)
            canvas.clipPath(path)
        }
        super.onDraw(canvas)
    }

    /**
     * Reads the "round_radius", "rounded_top_left", "rounded_top_right", "rounded_bottom_left",
     * "rounded_bottom_right" attributes from the view.
     */
    private fun readAttributes(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.RoundedCornerImageView)
            try {
                roundRadius = typedArray.getDimension(
                    R.styleable.RoundedCornerImageView_round_radius,
                    DEFAULT_ROUND_RADIUS
                )
                roundedTopLeft = typedArray.getBoolean(
                    R.styleable.RoundedCornerImageView_rounded_top_left,
                    false
                )
                roundedTopRight = typedArray.getBoolean(
                    R.styleable.RoundedCornerImageView_rounded_top_right,
                    false
                )
                roundedBottomLeft = typedArray.getBoolean(
                    R.styleable.RoundedCornerImageView_rounded_bottom_left,
                    false
                )
                roundedBottomRight = typedArray.getBoolean(
                    R.styleable.RoundedCornerImageView_rounded_bottom_right,
                    false
                )
            } finally {
                typedArray.recycle()
            }
        }
    }

    private fun buildRoundedCornersArray() {
        roundedCorners = mutableListOf()
        if (roundedTopLeft) {
            roundedCorners.add(ViewCornerUtils.ViewCorner.TOP_LEFT)
        }
        if (roundedTopRight) {
            roundedCorners.add(ViewCornerUtils.ViewCorner.TOP_RIGHT)
        }
        if (roundedBottomLeft) {
            roundedCorners.add(ViewCornerUtils.ViewCorner.BOTTOM_LEFT)
        }
        if (roundedBottomRight) {
            roundedCorners.add(ViewCornerUtils.ViewCorner.BOTTOM_RIGHT)
        }
    }

    companion object {
        private const val DEFAULT_ROUND_RADIUS = 8.0f
    }
}
