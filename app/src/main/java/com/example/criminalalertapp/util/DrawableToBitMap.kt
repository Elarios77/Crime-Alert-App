package com.example.criminalalertapp.util

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.Canvas

internal fun Drawable.toBitMap(): Bitmap? {

    if (this is BitmapDrawable) {
        return this.bitmap
    }

    val width = this.intrinsicWidth.coerceAtLeast(1)
    val height = this.intrinsicHeight.coerceAtLeast(1)

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, width, height)
    draw(canvas)
    return bitmap
}