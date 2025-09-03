package klimov.example.widget.widgets

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import com.google.android.material.color.DynamicColors
import klimov.example.widget.R
import klimov.example.widget.widgets.ui.AppWidgetContent
import klimov.example.widget.widgets.ui.AppWidgetLoadedData
import klimov.example.widget.widgets.utils.getSavedDynamicColorsChecked

class AppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val content = AppWidgetLoadedData(
            titleResId = R.string.app_widget_type_one_title,
            description = "Мое описание",
            value = 123
        )

        provideContent {
            val prefs = currentState<Preferences>()

            GlanceTheme {
                AppWidgetContent(
                    state = content,
                    isDynamicColorsAvailable = isDynamicColorsAvailable(prefs),
                    onRefreshClick = {  }
                )
            }
        }
    }

    private fun isDynamicColorsAvailable(prefs: Preferences): Boolean {
        val dynamicColorsChecked = prefs.getSavedDynamicColorsChecked()
        return dynamicColorsChecked && DynamicColors.isDynamicColorAvailable()
    }
}