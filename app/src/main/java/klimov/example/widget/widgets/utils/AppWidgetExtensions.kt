package klimov.example.widget.widgets.utils

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

private val KEY_DYNAMIC_COLORS_CHECKED = booleanPreferencesKey("widget_dynamic_colors_checked")

fun MutablePreferences.saveConfigurations(
    dynamicColorChecked: Boolean
) {
    this[KEY_DYNAMIC_COLORS_CHECKED] = dynamicColorChecked
}

fun Preferences.getSavedDynamicColorsChecked(): Boolean = this[KEY_DYNAMIC_COLORS_CHECKED] ?: true