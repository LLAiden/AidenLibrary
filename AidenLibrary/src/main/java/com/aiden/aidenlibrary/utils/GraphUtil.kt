package com.aiden.aidenlibrary.utils

import android.graphics.Path
import android.graphics.RectF

object GraphUtil {

    fun getAnglePath(rectF: RectF, radius: Float): Path {
        val path = Path()
        val floatArray = FloatArray(8) { radius }
        path.addRoundRect(rectF, floatArray, Path.Direction.CCW)
        return path
    }

    fun getAnglePath(rectF: RectF, leftTopRadius: Float, rightTopRadius: Float, rightBottomRadius: Float, leftBottomRadius: Float): Path {
        val path = Path()
        val floatArray =
            floatArrayOf(leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius)
        path.addRoundRect(rectF, floatArray, Path.Direction.CCW)
        return path
    }
}