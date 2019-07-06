package com.example.musicapp.utils

import android.graphics.*
import com.squareup.picasso.Transformation
import kotlin.math.roundToInt


class CircleTransform() : Transformation {
    private val strokePaint = Paint()

    init {
        strokePaint.style = Paint.Style.STROKE
        strokePaint.isAntiAlias = true
    }

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(
            squaredBitmap,
            Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )
        paint.shader = shader
        paint.isAntiAlias = true

        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)

        val strokeWidth = strokePaint.strokeWidth
        if (strokeWidth.roundToInt() != 0) {
            canvas.drawCircle(r + 0.5f, r + 0.5f, r - strokeWidth / 2 + 0.5f, strokePaint)
        }

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}
