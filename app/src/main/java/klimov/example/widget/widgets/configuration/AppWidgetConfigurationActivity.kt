package klimov.example.widget.widgets.configuration

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.getAppWidgetState
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.color.DynamicColors
import klimov.example.widget.ui.theme.WidgetTheme
import klimov.example.widget.widgets.AppWidget
import klimov.example.widget.widgets.utils.getSavedDynamicColorsChecked
import klimov.example.widget.widgets.utils.saveConfigurations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppWidgetConfigurationActivity : ComponentActivity() {
    private var appWidgetId: Int = AppWidgetManager.INVALID_APPWIDGET_ID

    private val viewModel: AppWidgetConfigurationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        val isDynamicColorsAvailable = DynamicColors.isDynamicColorAvailable()


        viewModel.viewCommand.flowWithLifecycle(lifecycle)
            .onEach { config -> saveWidgetState(config.isDynamicColorsChecked) }
            .launchIn(lifecycleScope)

        setContent {
            val state by viewModel.viewState.collectAsState(AppWidgetConfigurationState())

            WidgetTheme {
                AppWidgetConfigurationContent(
                    state = state,
                    isDynamicColorsAvailable = isDynamicColorsAvailable,
                    onDynamicColorsCheckedChange = { isDynamicColorChecked -> viewModel.setDynamicColorsChecked(isDynamicColorChecked) },
                    onBackClick = { onBackPressed() },
                    onSaveClick = { viewModel.saveConfig() }
                )
            }
        }
    }

    private fun initialize() {
        appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
        }

        lifecycleScope.launch {
            val glanceId = getGlanceId()
            val dataStore =
                getAppWidgetState(applicationContext, PreferencesGlanceStateDefinition, glanceId)

            val isDynamicColorsChecked = dataStore.getSavedDynamicColorsChecked()
            viewModel.setDynamicColorsChecked(isDynamicColorsChecked)
        }
    }

    private fun saveWidgetState(dynamicColorChecked: Boolean) =
        lifecycleScope.launch(Dispatchers.IO) {
            val glanceId = getGlanceId()
            updateAppWidgetState(applicationContext, glanceId) { prefs ->
                prefs.saveConfigurations(dynamicColorChecked)
            }
            AppWidget().update(applicationContext, glanceId)

            closeConfigurationActivity()
        }

    private fun closeConfigurationActivity() {
        val result = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        setResult(RESULT_OK, result)
        finish()
    }

    private fun getGlanceId(): GlanceId =
        GlanceAppWidgetManager(applicationContext).getGlanceIdBy(appWidgetId)
}