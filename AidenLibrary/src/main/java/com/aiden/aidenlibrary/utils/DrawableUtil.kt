package com.aiden.aidenlibrary.utils

import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt
import androidx.annotation.Size

object DrawableUtil {

    fun getGradientDrawable(@ColorInt color: Int, radius: Float, graph: Int = GradientDrawable.RECTANGLE): GradientDrawable {
        return GradientDrawable().apply {
            shape = graph
            setColor(color)
            cornerRadius = radius
        }
    }

    fun getGradientDrawable(@ColorInt color: Int, @Size(4) radius: FloatArray, graph: Int = GradientDrawable.RECTANGLE): GradientDrawable {
        return GradientDrawable().apply {
            shape = graph
            setColor(color)
            val radiusList =
                floatArrayOf(radius[0], radius[0], radius[1], radius[1], radius[2], radius[2], radius[3], radius[3])
            this.cornerRadii = radiusList
        }
    }

    fun setStrokeDrawable(drawable: GradientDrawable, width: Int, color: Int) {
        drawable.apply {
            setStroke(width, color)
        }
    }

    fun setGradientDrawable(drawable: GradientDrawable, colors: IntArray, gradientType: Int = GradientDrawable.LINEAR_GRADIENT) {
        drawable.apply {
            drawable.gradientType = gradientType
            drawable.colors = colors
        }
    }


}