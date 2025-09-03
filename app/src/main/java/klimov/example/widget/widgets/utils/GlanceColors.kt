package klimov.example.widget.widgets.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.glance.color.ColorProvider
import androidx.glance.unit.ColorProvider

@SuppressLint("RestrictedApi")
fun getPrimaryLabelColorProvider(): ColorProvider {
    val light = Color.Black

    return when (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
        true -> ColorProvider(day = light, night = Color.White)
        false -> ColorProvider(color = light)
    }
}

@SuppressLint("RestrictedApi")
fun getSecondaryLabelColorProvider(): ColorProvider {
    val light = Color(0xFF646464)

    return when (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
        true -> ColorProvider(day = light, night = Color(0xFFA0A0A0))
        false -> ColorProvider(color = light)
    }
}