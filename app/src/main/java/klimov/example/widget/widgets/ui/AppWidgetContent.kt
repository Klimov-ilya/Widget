package klimov.example.widget.widgets.ui

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

@Composable
fun AppWidgetContent(
    state: AppWidgetLoadedData,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(12.dp)
            .background(GlanceTheme.colors.widgetBackground)
    ) {
        AppWidgetTitleContent(state.titleResId, false, onRefreshClick)
    }
}

@Composable
private fun AppWidgetTitleContent(
    @StringRes titleResId: Int,
    isLoading: Boolean,
    onRefreshClick: () -> Unit
) {
    val context = LocalContext.current

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
                color = GlanceTheme.colors.onSurface
            )
        )

        when (isLoading) {
            true -> {
                CircularProgressIndicator(
                    modifier = GlanceModifier.size(24.dp),
                    color = GlanceTheme.colors.outline
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