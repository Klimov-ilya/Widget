package klimov.example.widget.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import klimov.example.widget.R
import klimov.example.widget.widgets.ui.AppWidgetContent
import klimov.example.widget.widgets.ui.AppWidgetLoadedData

class AppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val content = AppWidgetLoadedData(
            titleResId = R.string.app_widget_type_one_title,
            description = "Мое описание",
            value = 123
        )

        provideContent {
            GlanceTheme {
                AppWidgetContent(
                    state = content,
                    onRefreshClick = {  }
                )
            }
        }
    }
}