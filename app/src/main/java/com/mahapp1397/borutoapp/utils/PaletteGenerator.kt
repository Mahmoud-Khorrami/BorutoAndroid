package com.mahapp1397.borutoapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

object PaletteGenerator
{
    suspend fun ConvertImageUrlToBitmap(imageUrl: String, context: Context): Bitmap?
    {

        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context).data(imageUrl).allowHardware(false).build()
        val imageResult = loader.execute(request)

        return if (imageResult is SuccessResult)
            (imageResult.drawable as BitmapDrawable).bitmap
        else
            null
    }

    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String>
    {
        return mapOf(
            "vibrant" to parsColorSwatch(Palette.from(bitmap).generate().vibrantSwatch),

            "darkVibrant" to parsColorSwatch(Palette.from(bitmap).generate().darkVibrantSwatch),

            "onDarkVibrant" to parsBodyColor(Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor),

            )
    }

    private fun parsColorSwatch(color: Palette.Swatch?): String
    {
        return if (color != null)
        {
            val parsedColor = Integer.toHexString(color.rgb)
            "#$parsedColor"
        }
        else
            "#000000"
    }

    private fun parsBodyColor(color:Int?): String
    {
        return if (color != null)
        {
            val parsedColor = Integer.toHexString(color)
            "#$parsedColor"
        }
        else
            "#FFFFFF"
    }
}