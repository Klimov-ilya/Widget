package klimov.example.widget.widgets.ui

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import klimov.example.widget.R
import klimov.example.widget.widgets.utils.getPrimaryLabelColorProvider
import klimov.example.widget.widgets.utils.getSecondaryLabelColorProvider

@Composable
fun AppWidgetContent(
    state: AppWidgetLoadedData,
    isDynamicColorsAvailable: Boolean,
    onRefreshClick: () -> Unit
) {
    val columnModifier = GlanceModifier
        .fillMaxSize()
        .padding(12.dp)

    val backgroundModifier = when (isDynamicColorsAvailable) {
        true -> columnModifier.background(GlanceTheme.colors.widgetBackground)
        false -> {
            val backgroundRes = when (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
                true -> R.drawable.background_widget
                false -> R.drawable.background_widget_light_mode
            }
            columnModifier.background(ImageProvider(backgroundRes))
        }
    }

    Column(modifier = backgroundModifier) {
        AppWidgetTitleContent(
            titleResId = state.titleResId,
            isDynamicColorsAvailable = isDynamicColorsAvailable,
            isLoading = false,
            onRefreshClick = onRefreshClick
        )
    }
}

@Composable
private fun AppWidgetTitleContent(
    @StringRes titleResId: Int,
    isDynamicColorsAvailable: Boolean,
    isLoading: Boolean,
    onRefreshClick: () -> Unit
) {
    val context = LocalContext.current

    val onSurfaceColor = when (isDynamicColorsAvailable) {
        true -> GlanceTheme.colors.onSurface
        false -> getPrimaryLabelColorProvider()
    }
    val outLineColor = when (isDynamicColorsAvailable) {
        true -> GlanceTheme.colors.outline
        false -> getSecondaryLabelColorProvider()
    }

    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = context.getString(titleResId),
            modifier = GlanceModifier.defaultWeight(),
            maxLines = 1,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif,
                color = onSurfaceColor
            )
        )

        when (isLoading) {
            true -> {
                CircularProgressIndicator(
                    modifier = GlanceModifier.size(24.dp),
                    color = outLineColor
                )
            }
            false -> {
                Image(
                    provider = ImageProvider(R.drawable.vec_refresh_primary),
                    contentDescription = null,
                    modifier = GlanceModifier.clickable { onRefreshClick() }
                )
            }
        }
    }
}